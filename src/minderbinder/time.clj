(ns minderbinder.time
  (:require [minderbinder.core :refer [defunits-of]]))

(defunits-of time :millisecond
  ""
  :millisecond #{:ms :milliseconds}

  ;; A second is a duration of 9192631770 periods of the radiation
  ;; corresponding to the transition between the two hyperfine
  ;; levels of the ground state of the cesium-133 atom
  :second      1000
  :second      #{:sec :s :seconds}

  :minute      [60 :sec]
  :minute      #{:min :minutes :m}
  :hour        [60 :min]
  :hour        #{:hours :hr}
  :day         [24 :hour]
  :day         #{:d :da :days :julian-day :julian-days}
  :week        [7 :day]
  :week        #{:wk :sennight :weeks}
  :year        [365.25 :days]
  :year        #{:years :julian-year :julian-years}
  :leap-year   [366 :days]
  :decade      [10 :years]
  :century     [100 :years]
  :fortnight   [14 :day]
  :blink       [1/100000 :day]
  :ns          [1000000000 :seconds]
  :shake       [10 :ns]
  :jiffy       [1/100 :second]
  :moment      [90 :seconds]
  :dog-year    [52 :days]
  :mastery     [10000 :hours]
  :ce          [1/100 :day])

(comment
  (defmacro in [unit body]
    `(unit-of-time ~body ~unit))

  (minderbinder.time/in :seconds #unit/time [1000 :ms])

  (unit-of-time 1 :second)

  [#unit/time [1000 :ms]]

  (/ (unit-of-time 1 :second)
     (unit-of-time 1000 :ms))

  (/ (unit-of-time 1 :minute)
     (unit-of-time 1000 :ms))

)
