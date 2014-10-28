KoreanIPAProject
================

A project for converting Hangul to IPA
Although 'translating' other languages into IPA can be an ardous task at best, 
Korean with its phonetic alphabet, simple structure and few exceptions seems
like the perfect fit for this. There are however a few issues that still need
to be addressed in this project:
  Certain Korean words follow different rules depending on their origin.
The biggest culprits are the hanja-based words, of which an estimated 60-70%
of Korean words are based. Examples: 살인(hanja) vs 솔잎(traditional) where
the batchim ㄹ in traditional pronunciation converts the ㅇ into ㄹ but in
hanja-based words simply takes the silent ㅇs spot. 살인[사린] vs 솔잎[솔맆]
  The long-term plan has to be figuring out the identifying markers of these
'irregular' words and interfacing with or creating a database for word lookup.
