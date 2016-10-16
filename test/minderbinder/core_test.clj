(ns minderbinder.core-test
  (:require [minderbinder.core :as m]
            [clojure.test :refer [deftest is are testing]]))

(deftest build-conversion-map-test
  (testing "sets behave as aliases"
    (is (= (#'m/build-conversion-map :love
                                     [:kl [1000 :love]
                                      :kl #{:KL :Kl :kL}])
           (#'m/build-conversion-map :love
                                     [:kl [1000 :love]
                                      :KL :kl
                                      :Kl :kl
                                      :kL :kl])))))

(deftest defunit-of-test

  (testing "the base unit is 1"
    (is (= {:love 1}
           (m/defunits-of love :love
             "Have some")))
    ;; (is (= 10 (parse-love-unit [10 :love])))
    ;; (is (= 10 (unit-of-love 10 :love)))
    )


  (testing "can define simple units"
    (is (= {:love 1, :kl 1000}
           (m/defunits-of love :love
             "Have some"
             :kl 1000)))
    ;;(is (= 10000 (parse-love-unit [10 :kl])))
    ;;(is (= 10000 (unit-of-love 10 :kl)))
    )


  (testing "can have units depend on other units"
    (is (= {:love 1, :kl [1000 :love]}
           (m/defunits-of love :love
             "Have some"
             :kl [1000 :love])))
    ;; (is (= 10000 (parse-love-unit [10 :kl])))
    ;; (is (= 10000 (unit-of-love 10 :kl)))
    )

  (testing "can have aliases"
    (is (= {:love 1, :kl [1000 :love], :KL :kl}
           (m/defunits-of love :love
             "Have some"
             :kl [1000 :love]
             :KL :kl)))
    ;;(is (= 10000 (parse-love-unit [10 :kl])))
    ;;(is (= 10000 (unit-of-love 10 :kl)))
    )

  (testing "sets behave as aliases"
    (is (= {:love 1, :kl 1000, :KL :kl}
           (m/defunits-of love :love
             "Have some"
             :kl 1000
             :kl #{:KL})
           ))
    ;;(is (= 10000 (parse-love-unit [10 :kl])))
    ;;(is (= 10000 (unit-of-love 10 :kl)))
    )
  )
