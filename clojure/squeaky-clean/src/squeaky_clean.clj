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
  (replace-spaces  " *  Ab c de _ ")
  (clojure.test/run-tests 'squeaky-clean-test)
  )

(defn remove-non-letters [s]
  (str/replace s #"[^A-Za-zΑ-Ω_]" ""))
(comment
  (str/replace "MyΟβιεγτFinder" #"[^A-Za-zΑ-Ω_]" "")
  (str/replace "MyΟβιεγτΑΩFinder" #"[^A-Za-zΑ-Ω_]" "")
  (str/replace "a1_b2_" #"[^A-Za-z_]" "")
  (remove-non-letters "1My2Finder3")
  (clojure.test/run-tests 'squeaky-clean-test)
  )

(defn replace-control-char [s]
  (str/replace s #"[\u0000-\u001F\u007F-\u009F]" "CTRL"))
(comment
  (str/replace "my\u0080Id" #"[\u0000-\u001F\u007F-\u009F]" "CTRL")
  (replace-control-char "my\u0080Id")
  (clojure.test/run-tests 'squeaky-clean-test)
  )

(defn kebab-to-camel [s]
  (let [strVec (str/split s #"-")
        first (first strVec)
        capsVec (map str/capitalize (drop 1 strVec))
        returnVec (cons first capsVec)]
    (str/join returnVec)))
(comment
  (kebab-to-camel "this-is-fun")
  (kebab-to-camel "spatch-cock-bundle")
  (let [strVec (str/split "spatch-cock-bundle" #"-")
        first (first strVec)
        capsVec (map str/capitalize (drop 1 strVec))
        returnVec (cons first capsVec)]
    (str/join returnVec))
  (-> (str/split "spatch-cock-bundle" #"-")
      #_(apply str/capitalize)
      str/join)
  (clojure.test/run-tests 'squeaky-clean-test)
  )



(defn clean
  "returns a new string, based on the parameter after the following transformations
      - replace all spaces with underscores, including leading and trailing spaces
      - replace control characters with CTRL
      - remove all characters that are not letters or greek upper-case letters
      - convert kebab to camelCase
      "
  [s]
  (-> s replace-spaces
        replace-control-char
        kebab-to-camel
        remove-non-letters))

(comment
  (clean "1My2Finder3")
  (clean "  1My2 Finder 3")
  (clean "this-is-fun")
  (clean "à-ḃç")
  (clojure.test/run-tests 'squeaky-clean-test)
  )