(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (first deck)
)

(defn second-card
  "Returns the second card from deck."
  [deck]
  (first (drop 1 deck))
)

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [[first second & rest]]  
  (reduce conj rest [first second])
)

(defn discard-top-card
  "Returns a sequence containing the first card and
   a sequence of the remaining cards in the deck."
  [[first-card & rest]]
  (let [deck (if (nil? rest) nil
                           (vec rest))]
    [first-card deck]))
(comment
  (discard-top-card [7])
  (discard-top-card [9 2 10 4])
  (let [deck [9 2 10 4]
        first-card (first deck)
        rest (vec (drop 1 deck))]
    [first-card rest])
  (clojure.test/run-tests 'elyses-destructured-enchantments-test)
  )

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (if (= 0 (count deck))
    face-cards
    (vec (cons (first deck) (reduce conj face-cards (drop 1 deck)))))
)
(comment
  (let [deck [1 2 19]]
    #_(vec (drop 1 deck))

    #_(reduce conj face-cards (drop 1 deck))                  ; new deck except first card
    (vec (cons (first deck) (reduce conj face-cards (drop 1 deck))))
    #_(conj (first deck) (vec (drop 1 deck))))

  (insert-face-cards [])
  (insert-face-cards [1 2 19])
  (clojure.test/run-tests 'elyses-destructured-enchantments-test)
  )
