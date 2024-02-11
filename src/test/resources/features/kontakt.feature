@products
Feature: Add to Random Airpods to basket
Scenario: Random Airpods to basket
  Given Go to site
  When Check mainpage is open
  Then Close ads popup
  Then Insert Airpods to search field
  And Select first result
  And Add to cart
  And Go to cart
  Then Verify