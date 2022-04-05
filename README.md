## Zooplus Coding Exercise

## Delivery

### Prerequisites
1. Java 8
2. Maven

### Solution
1. Get the code
- If you have the zip file, unzip the zooplus.zip
```sh
unzip zooplus.zip
```
- If you want to clone from github, clone it
```sh
git clone https://github.com/cansaner/zooplus.git
```
2. Go to the folder
```sh
cd zooplus
```
3. Install it 
- If Maven is installed:
```sh
mvn clean install
```
- If you want to use Maven wrapper inside:
```bash
./mvnw clean install
```
4. Run it
- If Maven is installed:
```sh
mvn spring-boot:run
```
- If you want to use Maven wrapper inside:
```bash
./mvnw spring-boot:run
```

### Manual tests and results
* Germany IP 104.108.78.0, EUR German
![Germany](https://user-images.githubusercontent.com/937013/161748574-3a6197fd-3f4a-422b-9dd3-621a51004ce5.png)
* UK IP 102.165.31.0, GBP English
![UK](https://user-images.githubusercontent.com/937013/161748640-f10f3840-2e99-45f4-87fa-be421f48873f.png)
* Turkey IP 104.166.156.0, TRY Turkish
![Turkey](https://user-images.githubusercontent.com/937013/161748687-b8b6adc4-69d4-46b3-93cc-6ecce69f576e.png)
* France IP 104.107.108.0, EUR French
![France](https://user-images.githubusercontent.com/937013/161748761-449a300f-4551-400d-999c-26b071e33084.png)
* USA IP 101.36.98.0, USD English
![USA](https://user-images.githubusercontent.com/937013/161748801-57c8bb48-cc1f-4fb0-9870-781edddd681e.png)
* UAE IP 103.244.134.0, AED English
![UAE](https://user-images.githubusercontent.com/937013/161748826-87419d41-0f61-40a7-9e5e-413bafd8f4e8.png)
  
### Tasks
- JAVA spring web application to fetch the current localized price of a cryptocurrency is implemented
- The cryptocurrency price is displayed with currency symbol and correct decimal separator
- The application run and work correctly.
- Landing page with a dropbox to select the cryptocurrency and an input field to provided an optional IP . No need to use any framework, Thymeleaf or JSP is fine. 
- The locale should be retrieved from an optional user provided IP, in case it's not
  provided the IP from the request should be used
- The application have tests
- README.md file is included with all the relevant information for the result, such as
  assumptions or how to build and run the application.
- PR is created for the last commit to the repo because I have committed all other commits to the master branch already. For the last commit to add the fetching price feature to the showCryptoUnitPrice method is merged to master with a PR
- The solution is pushed to my github account under https://github.com/cansaner/zooplus

### Assumptions
- I assume that application should be able to run with java version 8.
- I assume that application will only fetch price data for a selected list of cryptocurrencies such as:
  * BTC, Bitcoin
  * ETH, Ethereum
  * ETH2, Ethereum 2
  * USDT, Tether
  * BNB, BNB
  * USDC, USD Coin
  * SOL, Solana
  * XRP, XRP
  * ADA, Cardano
  * LUNA, Terra
  * AVAX, Avalanche
  * DOT, Polkadot
  * DOGE, Dogecoin
  * BUSD, Binance USD
- I assume that application will fetch country and currency information of an ip from ip-api geolocation json endpoint: https://ip-api.com/docs/api:json#test
- I assume that application will fetch the spot price of a cryptocurreny from coinbase api: https://developers.coinbase.com/api/v2#get-spot-price
- I assume that application should be localized for German, French, Turkish, and English for default language
- I assume these Ips are relevant to test the application with currency codes:
  * Germany IP 104.108.78.0, EUR German
  * UK IP 102.165.31.0, GBP English
  * Turkey IP 104.166.156.0, TRY Turkish
  * France IP 104.107.108.0, EUR French
  * USA IP 101.36.98.0, USD English
  * UAE IP 103.244.134.0, AED English
- I couldn't find an API to convert currency code to currency symbol, so I have added it in LocaleUtils as a method. I have taken the currency code to currency symbol map from here: https://github.com/bengourley/currency-symbol-map/blob/master/map.js

### Bugs
- I can not display error messages for validations of the form fields
- Tests for IpApiServiceTest are not running, the reason is not understood yet. They are ignored currently
- 2 tests are failing in ViewControllerUnitTest when all tests are run together, there is a concurrency issue to be fixed. Tests are running fine when they are run particularly

### To-DO
- Spring annotations(Service, Controller, etc) should be replaced with manual bean injection
- Unit tests for models and proxy models may be added
- Tests for CoinbaseApiServiceTest are not written, because tests of IpApiServiceTest are not running and they will use similar setup, they have to be written after IpApiServiceTest tests are run without error