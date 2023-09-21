(ns bob)

(defn has-letters? [s]
  (not (nil? (re-find #"[a-zA-Z]+" s))))
(comment
  (let [s '("1,2,3A" "1,2s,3" "1,2,3")]
    (map has-letters? s))
  (let [s '("1,2,3A" "1,2s,3" "1,2,3")]
    (map #(re-find #"[a-zA-Z]+" %) s))
  (let [s "1,2,3"]
    (re-find #"[a-zA-Z]+" s))
  (clojure.test/run-tests 'bob-test)
  )

(defn is-silence? [s]
  (= "" (clojure.string/trim s)))
(comment
  (clojure.string/trim "")
  (= "" (clojure.string/trim ""))
  (is-silence? "
        ")
  (clojure.test/run-tests 'bob-test)
  )

(defn is-yelled? [s]
  (and (has-letters? s) (not (is-silence? s)) (= (clojure.string/upper-case s) s)))
(comment
  (clojure.test/run-tests 'bob-test)
  (is-yelled? "1, 2, 3")
  (is-yelled? "HI!")
  (is-yelled? "")
  (let [string "hi"]
    (= (clojure.string/upper-case string) string))
  (let [string "HI"]
    (= (clojure.string/upper-case string) string))
  (let [string "HI!"]
    (= (clojure.string/upper-case string) string))
  (let [string "Hi!"]
    (= (clojure.string/upper-case string) string))
  )

(defn is-question? [s]
  (= \? (last (clojure.string/trim s))))
(comment
  (is-question? "Is this a question?")
  (is-question? "This is not a question")
  (is-question? "Is this a question?  ")
  (clojure.test/run-tests 'bob-test)
  )

(defn is-silence? [s]
  (= "" (clojure.string/trim s)))
(comment
  (clojure.string/trim "")
  (= "" (clojure.string/trim ""))
  (is-silence? "
        ")
  (clojure.test/run-tests 'bob-test)
  )

(defn response-for [s] ;; <- arglist goes here
  ;; your code goes here
  (cond (and (is-yelled? s) (is-question? s)) "Calm down, I know what I'm doing!"
        (is-yelled? s)   "Whoa, chill out!"
        (is-question? s) "Sure."
        (is-silence? s)  "Fine. Be that way!"
        :else "Whatever."))
(comment
  (response-for "Okay if like my  spacebar  quite a bit?   ")
  (clojure.test/run-tests 'bob-test)
  )