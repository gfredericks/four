(ns four.bench
  (:require [criterium.core :refer [bench]]
            [four.stateless :as rnd]))

(set! *warn-on-reflection* true)

(defn bit-xor-1000-longs
  []
  (loop [i 0, x 0]
    (if (= 1000 i)
      x
      (recur (inc i) (bit-xor x (rand-int 2147483647))))))

(bench (bit-xor-1000-longs))
;; Evaluation count : 348780 in 60 samples of 5813 calls.
;;              Execution time mean : 177.653204 µs
;;     Execution time std-deviation : 6.225723 µs
;;    Execution time lower quantile : 167.286726 µs ( 2.5%)
;;    Execution time upper quantile : 190.993335 µs (97.5%)
;;                    Overhead used : 14.300149 ns

(defn bit-xor-1000-longs-java
  []
  (let [r (java.util.Random. 42)]
    (loop [i 0, x 0]
      (if (= 1000 i)
        x
        (recur (inc i) (bit-xor x (.nextLong r)))))))

(bench (bit-xor-1000-longs-java))
;; Evaluation count : 1218840 in 60 samples of 20314 calls.
;;              Execution time mean : 50.012172 µs
;;     Execution time std-deviation : 1.157078 µs
;;    Execution time lower quantile : 48.144161 µs ( 2.5%)
;;    Execution time upper quantile : 52.795407 µs (97.5%)
;;                    Overhead used : 14.300149 ns

;; Found 2 outliers in 60 samples (3.3333 %)
;; 	low-severe	 2 (3.3333 %)
;;  Variance from outliers : 11.0045 % Variance is moderately inflated by outliers

(defn bit-xor-1000-longs-four
  []
  (loop [i 0, x 0, r (rnd/java-random 42)]
    (if (= 1000 i)
      x
      (let [[^long x' r'] (rnd/next-long r)]
        (recur (inc i) (bit-xor x x') r')))))

(bench (bit-xor-1000-longs-four))
;; Evaluation count : 310800 in 60 samples of 5180 calls.
;;              Execution time mean : 198.223707 µs
;;     Execution time std-deviation : 8.820394 µs
;;    Execution time lower quantile : 184.141918 µs ( 2.5%)
;;    Execution time upper quantile : 219.729590 µs (97.5%)
;;                    Overhead used : 14.300149 ns

;; Found 4 outliers in 60 samples (6.6667 %)
;; 	low-severe	 3 (5.0000 %)
;; 	low-mild	 1 (1.6667 %)
;;  Variance from outliers : 30.3478 % Variance is moderately inflated by outliers
