(ns minderbinder.weight
  (:require [minderbinder.core :refer [defunits-of]]))

(defunits-of weight :kilogram
  "WiP"
  :kilogram 1
  :kg :kilogram
  ;;:kg [1 :kilogram]
  ;;:kg #{:kilograms :kilogram :grave}
  :g 1/1000
  :gram :g
  :grams :gram
  ;;:gram #{:grams :g} ;; TODO find why fails to compile NOTE: replacing :g
  ;;above leads to compilation failure. Need to chain the elements in the set to
  ;;avoid dependencies??

  :mg [1/1000 :g]
  :mg #{:milligrams :milligram}

  :hg [100 :grams]
  :hg #{:hectograms :hectogram}

  :dg [1/10 :g]
  :dg #{:decigrams :decigram}

  :dag [10 :g]
  :dag #{:decagrams :decagram}

  :cg [1/100 :g]
  :cg #{:centigram :centigrams}

  :Mg 1000
  :Mg #{:tonne :metric-ton :megagrams :megagram}

  :stone [6.35 :kg]

  :pound [45359237/100000000 :kg]
  :pound #{:lb :lbs :pounds}

  :ounce [1/16 :lb]
  :ounce #{:ounces :oz}

  :grain [64.79891 :mg]
  :grain #{:gr :troy-grain}

  :carat [200 :mg]
  :carat #{:metric-carat :CD}

  :pearl-grain [1/4 :carat]
  :pearl-grain :jewelers-grain

  ;; Celestial bodies

  :Gg [1000 :Mg]
  :Gg #{:gigagrams :gigagram}

  :Tg [1000 :Gg]
  :Tg #{:teragram :teragrams}

  :Pg [1000 :Tg]
  :Pg #{:petagram :petagrams}


  :Eg [1000 :Pg]
  :Eg #{:exagram :exagrams}

  :Zg [1000N :Eg]
  :Zg #{:zettagram :zettagrams}

  :Yg [1000 :Zg]
  :Yg #{:yottagram :yottagrams}

  ;; Microscopic bodies

  :mcg [1/1000 :g]
  :mcg #{:microgram :micrograms :µg}

  :ng [1/1000 :mcg]
  :ng #{:nanogram :nanograms}

  :pg [1/1000 :ng]
  :pg #{:picogram :picograms}

  :fg [1/1000 :pg]
  :fg #{:femtogram :femtograms}

  :ag [1/1000 :fg]
  :ag #{:attogram :attograms}

  :zg [1/1000 :ag]
  :zg #{:zeptogram :zeptograms}

  ;; :yg [1/1000 :yg]
  ;; :yg #{:yoctogram :yoctograms}
)
