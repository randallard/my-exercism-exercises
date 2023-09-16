(ns log-levels
  (:require [clojure.string :as str]))

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (last (re-find #".*:\s(.*)" s))
  )
(comment
  (clojure.test/run-tests 'log-levels-test)
  (last (re-find #".*:\s(.*)" "[ERROR]: Invalid operation"))
  )

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (clojure.string/lower-case (last (re-find #"\[(.*)\].*" s)))
  )
(comment
  (clojure.test/run-tests 'log-levels-test)
  (clojure.string/lower-case (last (re-find #"\[(.*)\].*" "[ERROR]: Invalid operation")))
  )

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (let [message (message s)
        level (log-level s)]
    (str message " (" level ")")))

(comment
  (clojure.test/run-tests 'log-levels-test)
  (reformat "[ERROR]: Invalid operation")
  )
