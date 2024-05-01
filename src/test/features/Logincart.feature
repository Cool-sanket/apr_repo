@Test
  Feature: Verifying E-commerce site login and adding items to cart

    @verifylogincart
    Scenario Outline: Verifying cart add items and total amounts

      Given Launch the browser and navigate to url
      When  we clicked on login link
      And   we entered username "<username>"
      And   we entered password "<password>"
      And   clicked on login button "<username>"
      And   searching for "Harry Potter"
      And   click items to add in cart
      And   Goto cart and get total amount "₹1,634.00"
      And   Add multiple items to cart and get total amt "₹3,268.00"
      Then  Verifying both total amount "₹1,634.00" "₹3,268.00"
      Examples:
      |username|password|
      |coolsanket|Sanket@123|
#      |Admin|admin123|




