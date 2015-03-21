# [four](http://xkcd.com/221/)

Four is a Clojure library for generating random numbers, with both
stateful and stateless generators.

## Stateless

Stateless generators are immutable (in contrast to java.util.Random
which all Clojure code I've seen uses), and the only one I've made so
far can only generate Longs, and tries (unsuccessfully?) to do so with
the same algorithm that java.util.Random uses.

## Stateful

So far this is just the `four.stateful` namespace with a `*rand*` dynamic
var and equivalents of `rand`, `rand-int`, `rand-nth`, and `shuffle`.

## Obtention

`[com.gfredericks/four "0.2.0"]`

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
