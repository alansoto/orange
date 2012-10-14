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
    
    

    /*
    //use this for test calls, otherwise comment
    url.GoogleAppsEngineBaseUrl = "http://orange.alansoto.com/TestCalculate.xml";
    */
    
    

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

    $('#electricity_year').text(

                    $(xml).find('electricityProduction year').text().toString()

            );
    $('#electricity_month').text(

                    $(xml).find('electricityProduction month').text().toString()

            );
    $('#electricity_week').text(

                    $(xml).find('electricityProduction week').text().toString()

            );


    $('#totalCost_year').text($(xml).find('totalCost year').text());
    $('#totalCost_month').text($(xml).find('totalCost month').text());
    $('#totalCost_week').text($(xml).find('totalCost week').text());

    $('#roi_year').text($(xml).find('returnOnInvestment year').text());
    $('#roi_month').text($(xml).find('returnOnInvestment month').text());
    $('#roi_week').text($(xml).find('returnOnInvestment week').text());

    $('#governmentRebates_year').text($(xml).find('governmentRebates year').text());
    $('#governmentRebates_month').text($(xml).find('governmentRebates month').text());
    $('#governmentRebates_week').text($(xml).find('governmentRebates week').text());

    $('#optimalFacingAngle_summer').text($(xml).find('optimalFacingAngle summer').text());
    $('#optimalFacingAngle_winter').text($(xml).find('optimalFacingAngle winter').text());

    //continue implementing here


}    
    
