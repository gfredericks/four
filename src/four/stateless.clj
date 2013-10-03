(ns four.stateless)

;; Should we use clj-tuple for these return values?
(defprotocol IRandom
  (next-long [r] "Generates the next long from the given Random. Returns [n r']."))

;;
;; Implementation that tries to copy java.util.Random as shown here:
;; http://developer.classpath.org/doc/java/util/Random-source.html
;;
;; Haven't actually been able to show that it does the same thing.
;;

(def ^:const java-foo
  ;; (dec (bit-shift-left 1 48))
  281474976710655)

(defn java-next-seed
  [^long seed]
  (-> seed
      (unchecked-multiply 0x5DEECE66D)
      (unchecked-add 0xB)
      (bit-and java-foo)))

(defrecord JavaRandom [seed]
  IRandom
  (next-long [_]
    (let [seed' (java-next-seed seed)
          seed'' (java-next-seed seed')
          x' (bit-shift-right seed' 16)
          x'' (bit-shift-right seed'' 16)
          x (+ (bit-shift-left x' 32) x'')
          r' (JavaRandom. seed'')]
      [x r'])))

(defn java-random
  [^long seed]
  (-> seed
      (unchecked-multiply 0x5DEECE66D)
      (bit-or 0xB)
      (bit-and java-foo)
      (->JavaRandom)))
