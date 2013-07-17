(ns poker.core)

(defn make-card [[s r]]
  {:suit ({\D :diamond \H :heart \C :club \S :spade} s) 
   :rank (.indexOf (seq "23456789TJQKA") r)}))
