NGrams
======

Description
-----------

Implementation of a collection of n-gram-based language model including
computation of unsmoothed unigrams and bigrams for an arbitrary text corpus. Current corpus
used as training is the King James Bible. The Bible corpus is broken up into documents in an
XML-style format, and the tags have been stripped away and only the text are aggregated in
developing your language model. The project also includes a random sentence generator for both
unigrams and bigrams. The last part of the project will consist of implementing Good-Turing
smoothing and add-one smoothing used later to implement the perplexity of a test set.

Class Descriptions
------------------

- `Bigrams.java` - Implementation of bigrams and the related smoothing features.
- `INgrams.java` - Interface for NGrams. Implemented by bigrams and unigrams.
- `NgramsInitializer` - Used to start the program by loading file to tokenize and populating bigrams/unigrams Tries.
- `Tokenizer.java` - Processes the corpus and strips away tags and formats it for NGrams.
- `Trie.java` - The data structure that holds information for NGrams language model. The Trie implementation has been altered from traditional character Trie to fit the tasks of this project better.
- `TrieNode.java` - A node class used to construct the Trie.
- `TrieTest.java` - A JUnit test for testing Trie implementation.
- `Unigrams.java` - Implementation of unigrams and the related smoothing features. The classes contains `WORD_MARKER` that specifies end of a word. It can be changed by the user.

How to run
----------

Run main in `NgramsInitializer` after providing the file paths to print unigrams and bigrams, then
choose the file containing corpus. (The current test set is in the project folder as `kjbible.test`)
Output of unigrams and bigrams written to files given as arguments to main.

