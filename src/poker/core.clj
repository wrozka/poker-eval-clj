(ns poker.core)

(defn make-card [[s r]]
  {:suit ({\D :diamond \H :heart \C :club \S :spade} s) 
   :rank (.indexOf (seq "23456789TJQKA") r)})

(defn eval-hand [hand]
    (let [hand (map (fn [[s r]] [s (.indexOf (seq "23456789TJQKA") r)]) hand)
          ranks (map last hand)
          flush (apply = (map first hand))
          straight? #(= (sort %) (map (partial + (apply min %)) (range 5)))
          straight (or (straight? ranks) (straight? (replace {12 -1} ranks)))
          freq (sort (vals (frequencies ranks)))]
      (cond
        (and flush straight) :straight-flush
        flush :flush
        straight :straight
        (= freq [1 4]) :four-of-a-kind
        (= freq [2 3]) :full-house
        (= freq [1 1 3]) :three-of-a-kind
        (= freq [1 2 2]) :two-pair
        (= freq [1 1 1 2]) :pair
        :else :high-card)))
