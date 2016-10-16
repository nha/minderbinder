(ns minderbinder.core)

(defn- relative-units [u units history]
  (if (some #{u} history)
    (throw (ex-info "Cycle detected " {:u u
                                       :history history}))
    (if-let [spec (u units)]
      (cond
        (vector? spec) (let [[conv to] spec]
                         (try
                           (* conv
                              (relative-units to units [u history]))
                           (catch ArithmeticException e
                             (throw (ArithmeticException. (str (.getCause e)
                                                               " in " spec))))))
        (keyword? spec) (relative-units spec units [u history])
        :default spec)
      (throw (ex-info "Undefined unit " {:u u})))))

(defn- build-conversion-map
  [base-unit unit-pairs]
  (into {base-unit 1}
        (reduce (fn [acc [k v]]
                  (if (set? v)
                    (concat acc (map vec (partition 2 (interleave v (repeat k)))))
                    (conj acc [k v])))
                [] (partition 2 unit-pairs))))

(defmacro defunits-of [quantity base-unit desc & units]
  (let [magnitude (gensym)
        unit (gensym)
        conversions (build-conversion-map base-unit units)
        conv-fn (symbol (str "parse-" quantity "-unit"))
        conv-mac (symbol (str "unit-of-" quantity))
        conv-table (symbol (str quantity "-table"))]
    `(do
       (defmacro ~conv-mac
         [~magnitude ~unit]
         `(* ~~magnitude
             ~(case ~unit
                ~@(mapcat
                    (fn [[u# & r#]]
                      `[~u# ~(relative-units u# conversions [])])
                    conversions))))

       (defn ~conv-fn
         [descr#]
         (let [conv# ~conversions]
           (reduce +
                   (map #(let [[mag# u#] %
                               r# (get conv# u#)]
                           (cond (keyword? r#) (~conv-fn [mag# r#])                   ;; Single alias
                                 (vector?  r#) (* mag# (~conv-fn r#))                 ;; Relative unit
                                 (map? r#)     (+ (* mag# (:scale r#)) (:offset r#))  ;; Scale and offset
                                 :default (* mag# r#)))                               ;; Assume numbers
                        (partition 2 descr#)))))

       (def ~conv-table ~conversions)
       ~conversions)))
