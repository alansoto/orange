/* Functions for  */



function getResults() {
    //make ajax call and bind results
    var url = new UrlBuilder();

    //use this for real ajax calls, otherwise comment
   url.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl; 
    url.Operation = "calculate";
    url.BatteryId = ($('#selectionBattery').text()).replace("battery_id_", "");
    url.PanelId = ($('#selectionSolarPanel').text()).replace("panel_id_", "");
    url.InverterId = ($('#selectionInverter').text()).replace("inverter_id_", "");
    url.Postcode = $('#txtUserPostcode').val();
    url.EnergyConsumption = $('#txtAverageConsumption').val();
    url.PanelCount = $('#selectionNumberOfPanels').text();
    
    

    
    //use this for test calls, otherwise comment
    //url.GoogleAppsEngineBaseUrl = "http://orange.alansoto.com/TestCalculate.xml";
    
    
    

    $.ajax({
        type: 'POST',
        url: 'proxy.aspx',
        dataType: 'xml',
        data: { servletCallUrl: url.toString() }
    })
    .done(bindResults) //bindResults() function will be called after a succesful request
    .fail(genericAjaxErrorHandler);  

}

//reads xml and bind results into respective elements in Calculate.aspx (see id=wizardResults table)
function bindResults(xml) {
    var h = new HelperFunctions();

    //read variables
    var electricityProductionYear = $(xml).find('electricityProduction year').text().toString();
    var electricityProductionMonth = $(xml).find('electricityProduction month').text().toString();
    var electricityProductionWeek = $(xml).find('electricityProduction week').text().toString();
    var totalCostYear =$(xml).find('totalCost year').text();
    var totalCostMonth = h.roundNumber($(xml).find('totalCost month').text() / 12, 2);
    var totalCostWeek = h.roundNumber($(xml).find('totalCost week').text() / 52, 2)
    var roiYear = $(xml).find('returnOnInvestment year').text();
    var roiMonth = $(xml).find('returnOnInvestment month').text();
    var roiWeek = $(xml).find('returnOnInvestment week').text();
    var governmentRebatesYear = $(xml).find('governmentRebates year').text();
    var governmentRebatesMonth = h.roundNumber($(xml).find('governmentRebates year').text() / 12, 2);
    var governmentRebatesWeek = h.roundNumber($(xml).find('governmentRebates year').text() / 52, 2);
    var savingsYear = $(xml).find('savings year').text();
    var savingsMonth = $(xml).find('savings month').text();
    var savingsWeek = $(xml).find('savings week').text();
    var sunIntensity = $(xml).find('sunIntensity').text();
    var dailySunHours = $(xml).find('dailySunHours').text();

    //bind variables with respective units of measure
    $('#electricity_year').text(electricityProductionYear + " KWH");
    $('#electricity_month').text(electricityProductionMonth + " KWH");
    $('#electricity_week').text(electricityProductionWeek + " KWH");
    $('#totalCost_year').text("$"+totalCostYear);
    $('#totalCost_month').text("$" + totalCostMonth);
    $('#totalCost_week').text("$" + totalCostWeek);

    $('#roi_year').text(roiYear + '%');
    $('#roi_month').text(roiMonth + '%');
    $('#roi_week').text(roiWeek + '%');

    $('#governmentRebates_year').text("$" + governmentRebatesYear);
    $('#governmentRebates_month').text("$" + governmentRebatesMonth);
    $('#governmentRebates_week').text("$" + governmentRebatesWeek);
    
    //savings
    $('#savings_year').text("$" + savingsYear);
    $('#savings_month').text("$" +  + savingsMonth);
    $('#savings_week').text("$" + savingsWeek);

    $('#sunIntensity').html(sunIntensity + ' WATTS / M <sup>2</sup>');
    $('#dailySunHours').text(dailySunHours + ' HOURS');

}    
    
