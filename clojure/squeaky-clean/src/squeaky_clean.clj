(ns squeaky-clean
  (:require [clojure.string :as str]))

; replace any spaces with underscores - including any leading and trailing spaces
(defn replace-spaces [s]
  (str/replace s \space \_)
  )
(comment
  (let [s " A"]
    (str/replace s \space \_))
  (replace-spaces " A")
  (clojure.test/run-tests 'squeaky-clean-test)
  )

(defn clean
  "TODO: add docstring"
  [s]
  )

(comment
  (clojure.test/run-tests 'squeaky-clean-test)
  )