(ns poker.core)

(defn make-card [[s r]]
  {:suit ({\D :diamond \H :heart \C :club \S :spade} s) 
   :rank (.indexOf (seq "23456789TJQKA") r)})

(defn eval-hand [hand]
  (letfn [(make-card [[s r]]
            {:suit ({\D :diamond \H :heart \C :club \S :spade} s) 
             :rank (.indexOf (seq "23456789TJQKA") r)})
          (low-to-high? [ranks]
            (let [high-card (last (sort ranks))
                  low-card (first (sort ranks))]
              (= (+ 4 low-card) high-card)))
          (normalize-ace [ranks]
            (map #(mod (+ % 1) 13) ranks)) 
          (straight? [ranks]
            (and (= (count (distinct ranks)) 5)
              (or (low-to-high? ranks) (low-to-high? (normalize-ace ranks)))))
          (flush? [suits]
            (= (count (distinct suits)) 1))
          (distribution [ranks]
            (vals
              (reduce
                (fn [stats rank] (conj stats [rank (+ 1 (stats rank 0))])) 
                {}
                ranks)))]
    (let [hand (map make-card hand)
          ranks (map :rank hand)
          suits (map :suit hand)
          rank-counts (distribution ranks)
          same-rank-max (apply max rank-counts)
          pairs (count (filter #(= % 2) rank-counts))]
      (cond
        (= same-rank-max 4) :four-of-a-kind
        (and (flush? suits) (straight? ranks)) :straight-flush
        (flush? suits) :flush
        (straight? ranks) :straight
        (and (= same-rank-max 3) (= pairs 1)) :full-house
        (= same-rank-max 3) :three-of-a-kind
        (= pairs 2) :two-pair
        (= pairs 1) :pair
        :else :high-card))))
