(ns poker.t-core
  (:use midje.sweet)
  (:use [poker.core]))

(facts "about `make-card`"
  (make-card "DA") => {:suit :diamond :rank 12}
  (make-card "HK") => {:suit :heart :rank 11}
  (make-card "CQ") => {:suit :club :rank 10}
  (make-card "SJ") => {:suit :spade :rank 9}
  (make-card "DT") => {:suit :diamond :rank 8}
  (make-card "D9") => {:suit :diamond :rank 7}
  (make-card "D8") => {:suit :diamond :rank 6}
  (make-card "D7") => {:suit :diamond :rank 5}
  (make-card "D6") => {:suit :diamond :rank 4}
  (make-card "D5") => {:suit :diamond :rank 3}
  (make-card "D4") => {:suit :diamond :rank 2}
  (make-card "D3") => {:suit :diamond :rank 1}
  (make-card "D2") => {:suit :diamond :rank 0})

(facts "about `eval-hand`"
  (eval-hand ["HA" "D2" "H3" "C9" "DJ"]) => :high-card
  (eval-hand ["HA" "HQ" "SJ" "DA" "HT"]) => :pair
  (eval-hand ["HA" "DA" "HQ" "SQ" "HT"]) => :two-pair
  (eval-hand ["HA" "DA" "CA" "HJ" "HT"]) => :three-of-a-kind
  (eval-hand ["HA" "DK" "HQ" "HJ" "HT"]) => :straight
  (eval-hand ["HT" "D9" "H8" "H7" "H6"]) => :straight
  (eval-hand ["HA" "H2" "S3" "D4" "C5"]) => :straight
  (eval-hand ["HA" "HK" "H2" "H4" "HT"]) => :flush
  (eval-hand ["HA" "DA" "CA" "HJ" "DJ"]) => :full-house
  (eval-hand ["HA" "DA" "CA" "SA" "DJ"]) => :four-of-a-kind
  (eval-hand ["HA" "HK" "HQ" "HJ" "HT"]) => :straight-flush)
