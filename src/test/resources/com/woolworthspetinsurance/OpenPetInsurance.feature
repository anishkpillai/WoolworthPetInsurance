Feature: Open WoolworthsPetInsurance site and verify menu
  Test Pet Insurance site for page title and menu headings

  Background: Navigate to Woolworths Pet Insurance site
    Given the Woolworths PetInsurance website

  Scenario: Open home page and verify title/heading
    Then verify page title is blank
    Then verify heading has text "Woolworths Pet Insurance Quote and Application"

  Scenario: Verify the different steps in quote page
    Then verify list of steps in quote page
      | About you and your pet     |
      | Quote and product details |
      | Confirmation and Payment  |
