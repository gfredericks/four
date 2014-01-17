(ns four.stateful
  (:refer-clojure :exclude [rand rand-int rand-nth shuffle]))

(def ^:dynamic ^java.util.Random *rand*
  (java.util.Random.))

(defn rand [] (.nextDouble *rand*))
(defn rand-int
  [n]
  (int (* n (rand))))

(defn rand-nth
  [coll]
  (nth coll (rand-int (count coll))))

(defn shuffle
  "Return a random permutation of coll"
  [^java.util.Collection coll]
  (let [al (java.util.ArrayList. coll)]
    (java.util.Collections/shuffle al *rand*)
    (clojure.lang.RT/vector (.toArray al))))
