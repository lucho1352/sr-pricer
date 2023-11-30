--Populate script for BRAND Table
INSERT INTO BRAND (ID, NAME) VALUES (1, 'ZARA');

--Populate script for CURRENCY table
INSERT INTO CURRENCY (ID, DESCRIPTION) VALUES (1, 'EUR');

--Populate script for PRICE table
INSERT INTO PRICES (BRAND_ID, PRODUCT_ID, PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY_ID) VALUES (1, 35455, 1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 0, 35.50, 1);
INSERT INTO PRICES (BRAND_ID, PRODUCT_ID, PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY_ID) VALUES (1, 35455, 2, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 1, 25.45, 1);
INSERT INTO PRICES (BRAND_ID, PRODUCT_ID, PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY_ID) VALUES (1, 35455, 3, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 1, 30.50, 1);
INSERT INTO PRICES (BRAND_ID, PRODUCT_ID, PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURRENCY_ID) VALUES (1, 35455, 4, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 1, 38.95, 1);