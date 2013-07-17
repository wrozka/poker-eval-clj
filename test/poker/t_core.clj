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

