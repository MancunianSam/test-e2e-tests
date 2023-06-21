Feature: Uploading things

  Scenario: Upload to DR works
    Given An upload into the test bucket
    Then The file should be in the DR bucket

