# ParaBank Automation Framework

## Project Overview
This is an end-to-end (E2E) test automation framework based on Playwright, designed for testing the ParaBank online banking application. The framework covers both UI and API test scenarios, ensuring robust validation of critical banking functionalities.

ParaBank is a real-world online banking application that enables users to manage financial transactions. This automation framework tests the complete workflow from user registration to account management, fund transfers, and bill payments.

## Key Features
- Comprehensive test coverage: Includes both UI and API test scenarios  
- Modular design: Implements Page Object Model (POM) pattern for improved code reusability  
- Dynamic data generation: Generates unique random usernames for each test execution  
- Detailed assertions: Includes necessary validations at each test step  
- Configurable: Supports multiple execution modes and report generation  

## Test Scenarios

### UI Test Scenarios
1. Navigate to ParaBank application  
2. User registration (with randomly generated unique username)  
3. User login  
4. Validate global navigation menu  
5. Create savings account and record account number  
6. Verify account overview page  
7. Fund transfer  
8. Bill payment  
   - Each step includes necessary assertions  

### API Test Scenarios
1. Use "Find Transactions" API to search payment transactions by amount  
2. Validate transaction details in JSON response  

## Known Issues and Limitations
- ​**CAPTCHA Verification**: The system occasionally displays CAPTCHA challenges to confirm human operators, which currently remains unhandled. Additional logic can be implemented later if needed.  
- ​**System Inherent Defects**:  
  - On the registration page, the validation message for excessively long usernames is incorrect (currently displays "This username already exists.")  
  - While covering all business processes, the framework currently lacks dedicated boundary condition tests to uncover system defects  

## Quick Start
### Prerequisites
- Java JDK 11 or later  
- Maven 3.6.0 or later  

### Basic Commands
1. Full build with test execution:  
   `mvn clean install`  

2. Build without running tests:  
   `mvn clean install -DskipTests`  

3. Run tests only:  
   `mvn test`  

## Test Reports
After test completion, reports can be found at:  
`.txt files in target/surefire-reports/ directory`
