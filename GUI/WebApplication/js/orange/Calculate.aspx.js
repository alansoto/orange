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


    //electricityProduction
    var strAllElectricityProduction = $(xml).find('electricityProduction years').text();
    var arrayElectricityProduction = strAllElectricityProduction.split(';');
    var electricityArray = [[
        [1, arrayElectricityProduction[1]],
        [2, arrayElectricityProduction[2]],
        [3, arrayElectricityProduction[3]],
        [4, arrayElectricityProduction[4]],
        [5, arrayElectricityProduction[5]],
        [6, arrayElectricityProduction[6]],
        [7, arrayElectricityProduction[7]],
        [8, arrayElectricityProduction[8]],
        [9, arrayElectricityProduction[9]],
        [10, arrayElectricityProduction[10]],
        [11, arrayElectricityProduction[11]],
        [12, arrayElectricityProduction[12]],
        [13, arrayElectricityProduction[13]],
        [14, arrayElectricityProduction[14]],
        [15, arrayElectricityProduction[15]],
        [16, arrayElectricityProduction[16]],
        [17, arrayElectricityProduction[17]],
        [18, arrayElectricityProduction[18]],
        [19, arrayElectricityProduction[19]],
        [20, arrayElectricityProduction[20]],
        [21, arrayElectricityProduction[21]],
        [22, arrayElectricityProduction[22]],
        [23, arrayElectricityProduction[23]],
        [24, arrayElectricityProduction[24]],
        [25, arrayElectricityProduction[25]],
    ]]; 
    $.plot($("#graphElectricity"),electricityArray,{});


    //savings
    var strAllSavings = $(xml).find('savings years').text();
    var arraySavings = strAllSavings.split(';');
    var SavingsArray = [[
        [1, arraySavings[1]],
        [2, arraySavings[2]],
        [3, arraySavings[3]],
        [4, arraySavings[4]],
        [5, arraySavings[5]],
        [6, arraySavings[6]],
        [7, arraySavings[7]],
        [8, arraySavings[8]],
        [9, arraySavings[9]],
        [10, arraySavings[10]],
        [11, arraySavings[11]],
        [12, arraySavings[12]],
        [13, arraySavings[13]],
        [14, arraySavings[14]],
        [15, arraySavings[15]],
        [16, arraySavings[16]],
        [17, arraySavings[17]],
        [18, arraySavings[18]],
        [19, arraySavings[19]],
        [20, arraySavings[20]],
        [21, arraySavings[21]],
        [22, arraySavings[22]],
        [23, arraySavings[23]],
        [24, arraySavings[24]],
        [25, arraySavings[25]],
    ]];
    $.plot($("#graphSavings"), SavingsArray, {});


    //ROI
    var strAllROI = $(xml).find('returnOnInvestment years').text();
    var arrayROI = strAllROI.split(';');
    var ROIArray = [[
        [1, arrayROI[1]],
        [2, arrayROI[2]],
        [3, arrayROI[3]],
        [4, arrayROI[4]],
        [5, arrayROI[5]],
        [6, arrayROI[6]],
        [7, arrayROI[7]],
        [8, arrayROI[8]],
        [9, arrayROI[9]],
        [10, arrayROI[10]],
        [11, arrayROI[11]],
        [12, arrayROI[12]],
        [13, arrayROI[13]],
        [14, arrayROI[14]],
        [15, arrayROI[15]],
        [16, arrayROI[16]],
        [17, arrayROI[17]],
        [18, arrayROI[18]],
        [19, arrayROI[19]],
        [20, arrayROI[20]],
        [21, arrayROI[21]],
        [22, arrayROI[22]],
        [23, arrayROI[23]],
        [24, arrayROI[24]],
        [25, arrayROI[25]],
    ]];
    $.plot($("#graphRoi"), ROIArray, {});

    //GovernmentRebates
    var strAllGovernmentRebates = $(xml).find('governmentRebates years').text();
    var arrayGovernmentRebates = strAllGovernmentRebates.split(';');
    var GovernmentRebatesArray = [[
        [1, arrayGovernmentRebates[1]],
        [2, arrayGovernmentRebates[2]],
        [3, arrayGovernmentRebates[3]],
        [4, arrayGovernmentRebates[4]],
        [5, arrayGovernmentRebates[5]],
        [6, arrayGovernmentRebates[6]],
        [7, arrayGovernmentRebates[7]],
        [8, arrayGovernmentRebates[8]],
        [9, arrayGovernmentRebates[9]],
        [10, arrayGovernmentRebates[10]],
        [11, arrayGovernmentRebates[11]],
        [12, arrayGovernmentRebates[12]],
        [13, arrayGovernmentRebates[13]],
        [14, arrayGovernmentRebates[14]],
        [15, arrayGovernmentRebates[15]],
        [16, arrayGovernmentRebates[16]],
        [17, arrayGovernmentRebates[17]],
        [18, arrayGovernmentRebates[18]],
        [19, arrayGovernmentRebates[19]],
        [20, arrayGovernmentRebates[20]],
        [21, arrayGovernmentRebates[21]],
        [22, arrayGovernmentRebates[22]],
        [23, arrayGovernmentRebates[23]],
        [24, arrayGovernmentRebates[24]],
        [25, arrayGovernmentRebates[25]],
    ]];
    $.plot($("#graphRebates"), GovernmentRebatesArray, {});


}    
    
