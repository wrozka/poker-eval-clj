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
          rank-counts (vals (frequencies ranks))
          same-rank-max (apply max rank-counts)
          pairs (count (filter #(= % 2) rank-counts))]
      (cond
        (= same-rank-max 4) :four-of-a-kind
        (and flush straight) :straight-flush
        flush :flush
        straight :straight
        (and (= same-rank-max 3) (= pairs 1)) :full-house
        (= same-rank-max 3) :three-of-a-kind
        (= pairs 2) :two-pair
        (= pairs 1) :pair
        :else :high-card)))
