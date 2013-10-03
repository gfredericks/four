# [four](http://xkcd.com/221/)

Four is a Clojure library for generating random numbers.

The only implementation so far is immutable (in contrast to
java.util.Random which all Clojure code I've seen uses), can only
generate Longs, and tries (unsuccessfully?) to do so with the same
algorithm that java.util.Random uses.

If anybody has need for more features, I imagine adding:

- Different types
- More implementations
- A `four.stateful` namespace that includes a dynamic *rand* var
  that could be used from other libraries (since clojure.core lacks
  this)

## Obtainage

`[com.gfredericks/four "0.1.0"]`

## Usage

```clojure
(require '[four.stateless :as rnd])

(let [r (rnd/java-random 42)]
  (rnd/next-long r))
;; => [-5507218355876663470 #four.stateless.JavaRandom{:seed 129149386787357}]
```

## License

Copyright © 2013 Gary Fredericks

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
