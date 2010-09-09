require 'set'

module Dictionary
  
  WORDS = Set.new(File.read("dict/3letters").split("\n").compact + File.read("dict/4letters").split("\n").compact)
  
  def self.is_word?(word)
    WORDS.include?(word)
  end
  
  # scrabble scores
  LETTER_SCORES = {
    "A" => 1,
    "B" => 3,
    "C" => 3,
    "D" => 2,
    "E" => 1,
    "F" => 4,
    "G" => 2,
    "H" => 4,
    "I" => 1,
    "J" => 8,
    "K" => 5,
    "L" => 1,
    "M" => 3,
    "N" => 1,
    "O" => 1,
    "P" => 3,
    "Q" => 10,
    "R" => 1,
    "S" => 1,
    "T" => 1,
    "U" => 1,
    "V" => 4,
    "W" => 4,
    "X" => 8,
    "Y" => 4,
    "Z" => 10
  }
  
  def self.letter_score(letter)
    LETTER_SCORES[letter]
  end

  def self.scrabble_score(word)
    word.split("").inject(0) {|sum, letter| sum + letter_score(letter)}
  end
  
end

# for each word in the dictionary
# verify it is a word
# and calculate its score
n = (ARGV[0] || 10).to_i

puts "#{n} repeats"
n.times do
  
  Dictionary::WORDS.each do |word|
    Dictionary.is_word?(word)
    Dictionary.scrabble_score(word)
  end

end
