(ns interest-is-interesting
  (:require [clojure.math]))

(defn interest-rate
  "Returns the interest rate based on the specified balance."
  [balance]
  (cond (>= balance 5000)  2.475
        (>= balance 1000)  1.621
        (>= balance 0)     0.5
    :else -3.213)
  )
(comment
  (clojure.test/run-tests 'interest-is-interesting-test)
  )

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate."
  [balance]
  )
(comment
  (clojure.test/run-tests 'interest-is-interesting-test)
  )

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free percentage."
  [balance tax-free-percentage]
  (int (* 2 (* balance tax-free-percentage)))
  )
(comment
  (clojure.test/run-tests 'interest-is-interesting-test)
  )

(comment
  (clojure.test/run-tests 'interest-is-interesting-test)
  )