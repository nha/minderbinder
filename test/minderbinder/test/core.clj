(ns minderbinder.test.core
  (:require minderbinder.core
            minderbinder.time
            [clojure.test :refer [deftest is]]))

(deftest test-time-units
  (is (= 1000 (minderbinder.time/parse-time-unit [1 :second])))
  (is (= 1000 #unit/time [1 :second])))
