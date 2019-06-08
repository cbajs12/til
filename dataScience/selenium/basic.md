### selenium basic

#### 필요사항
1. Selenium
2. Webdriver - Chrome, Safari, Firefox ...

* Chrome
> https://sites.google.com/a/chromium.org/chromedriver/

#### Setting
1) Set Property
```java
if(System.getProperty("os.name").toLowerCase().contains("mac")){
System.setProperty("webdriver.chrome.driver", "driver location");}else if(System.getProperty("os.name").toLowerCase().contains("linux")){       System.setProperty("webdriver.chrome.driver", "driver location");}
```
2) Get Object
` private ChromeDriver driver = new ChromeDriver();`

3) Operate browser
` driver.get(url);`

4) Close browser
`driver.close();`

#### Handling WebContent
1. Find by element id
`WebElement query = driver.findElement(By.id("id"));`

2. Find by css selector
`driver.findElements(By.cssSelector("div.class"));`

3. Move child or parent element
`webElements.get(i).findElement(By.xpath(".//a"))` 

4. Input data & submit
`query.sendKeys(name); query.submit();`

5. Click css button
`driver.findElement(By.cssSelector("a.next")).click();`

6. Javascript Injection
`((JavascriptExecutor)driver).executeScript("return document.readyState")`
