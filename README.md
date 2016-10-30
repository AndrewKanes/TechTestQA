# TechTestQA

# PLEASE UPLOAD YOUR WORKING SOLUTION TO GITHUB FOR REVIEW

# Write a program that generates X random character pairs
# and analyzes them using the following rules:
#
# 1. Each pair should be one number (1-99) and one letter (a-z)
#
# 2. Prime numbers should be Y times more likely than other numbers
#
# 3. Perfects squares should be one third (1/3)x as likely as prime numbers 
#
# 4. Vowels (a, e, i, o, u) should be Z times more likely than consonants
#
# 5. The letter "y" should be twice (2x) as likely as vowels
#
# 6. Each letter must be translated to a number based on the its numerical 
#    position in the alphabet.
#    ex: 'f' is the 6th letter of the alphabet, so it would be evaluated as 6. 
#
# 7. This program should return valid JSON that has the following structure:
#
# {
#    "letters": {"wins": 4000, "streak": 17},
#    "numbers": {"wins": 6000, "streak": 6}
# }
#
#
# 8. Each number/letter value pair should be evaluated. If the letter value is lower,
#    incrememnt the "wins" field on the "letters" object. If the number value is lower, 
#    increment the "wins" field on the "numbers" object.
#
# 9. Keep track of the longest running "streak" for letters and numbers
#    
#    ex: if "letters" is lower 5 times in a row, the letters streak would be 5
#
# 10. This program should be able to take 3 inputs:
#  a. the number of "challenges" (X)
#  b. the likelihood of prime numbers (Y)
#  c. the likelihood of vowels (Z)

# - Please run the code using various inputs for X, Y, and Z
# - Make sure at least one of your runs does 1,000,000 challenges (X = 1000000)
# - Is there an input combination where the numbers would have higher wins?
#
# analyze your results, and describe what they mean
# please submit your code for review in github
