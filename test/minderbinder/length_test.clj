(ns minderbinder.length-test
  (:require [minderbinder.length :as mbr]
            [clojure.test :refer [deftest is are run-tests testing]]))


(defn str->meters [s]
  (try
    (let [d (Double/parseDouble (re-find (re-pattern "\\d+") s))
          unit (keyword (or (re-find (re-pattern "[a-zA-Z]+") s) "m"))]
      (mbr/parse-length-unit [d unit]))
    (catch Throwable t
      nil)))

(defn meter [x]
  (cond
    (string? x)  str->meters
    (number? x) x
    :else nil))

(deftest str->meters-test
  (testing "can convert from string to meters"
    (is (= 0.001 (str->meters "1mm")))
    (is (= 0.001 (str->meters "1mm")))))
