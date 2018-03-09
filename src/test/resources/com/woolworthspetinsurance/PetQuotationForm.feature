Feature: Verify form details and Pet Quotation form
  Test Pet quotation form and the quote estimate by adding pets

  Background: Navigate to Woolworths Pet Insurance site
    Given the Woolworths PetInsurance website
  @Important
  Scenario: Verify Pet quotation individual headings
    Then verify 1 pet heading as "Your pet details"
    When add more pet button is clicked
    Then verify 1 pet heading as "Your pet details"
    And verify 2 pet heading as "Your 2nd pet details"

  @dont
  Scenario: Fill Pet details and confirm the quote estimate
    When the pet quotation form is filled with below details
      | species | name  | breed      | day | month | year | gender | desexed |
      | Dog     | Jimmy | Pomeranian |  12 |    02 | 2015 | Male   | No      |
      | Dog     | Spike | Pomeranian |  12 |    12 | 2015 | Female | No      |
      | Cat     | Tom   | Himalayan  |  11 |    11 | 1999 | Female | Yes     |
    And enter your date of birth as 04/01/1982
    And enter your postcode as 2148
    #And promocode is entered as "W50" if not prefilled
    And email is filled as "aku@yopmail.com"
    And get quote button is clicked
    #Then promotion modal popup is displayed
    #And verify the popup title as "Promotion Message"
    #When the continue button is clicked in popup
    Then verify cover options for pet named "Spike" and breed "Pomeranian" with below details
      | packagetitle  | subtitle                                    | price  | period        |
      | Basic         | Accidental Injury and Illness               | $12.00 | per fortnight |
      | Standard      | Accidental Injury and Illness               | $15.64 | per fortnight |
      | Comprehensive | Accidental Injury & Illness Cover + Routine | $20.78 | per fortnight |
    Then verify cover options for pet named "Jimmy" and breed "Pomeranian" with below details
      | packagetitle  | subtitle                                    | price  | period        |
      | Basic         | Accidental Injury and Illness               | $11.65 | per fortnight |
      | Standard      | Accidental Injury and Illness               | $15.46 | per fortnight |
      | Comprehensive | Accidental Injury & Illness Cover + Routine | $20.60 | per fortnight |
    Then verify cover options for pet named "Tom" and breed "Himalayan" with below details
      | packagetitle | subtitle          | price  | period      |
      | Basic        | Accidental Injury and Illness | $16.25 | per fortnight |
