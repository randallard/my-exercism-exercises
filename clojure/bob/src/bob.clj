(ns bob)

(defn is-yelled? [s]
  (= (clojure.string/upper-case s) s))
(comment
  (let [string "hi"]
    (= (clojure.string/upper-case string) string))
  (let [string "HI"]
    (= (clojure.string/upper-case string) string))
  (let [string "HI!"]
    (= (clojure.string/upper-case string) string))
  (let [string "Hi!"]
    (= (clojure.string/upper-case string) string))
  (is-yelled? "HI!")
  (clojure.test/run-tests 'bob-test)
  )

(defn is-question? [s]
  (= \? (last s)))
(comment
  (is-question? "Is this a question?")
  (is-question? "This is not a question")
  (clojure.test/run-tests 'bob-test)
  )

(defn response-for [s] ;; <- arglist goes here
  ;; your code goes here
  (cond (is-yelled? s)   "Whoa, chill out!"
        (is-question? s) "Sure."
        :else "Whatever."))

(comment
  (clojure.test/run-tests 'bob-test)
  )