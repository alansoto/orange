<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Calculate.aspx.cs" Inherits="Calculate" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div class="container_12 content">
        <div class="grid_12" id="divMainTitle">
            <h1>CALCULATE YOUR SYSTEM FITNESS</h1>
        </div>
        <div class="clear"></div>

        <div id="divWelcomePanel" class="grid_12">
            <h3>THIS WIZARD WILL ALLOW YOU TO ACCURATELY CALCULATE ENERGY EFFICIENCY FOR YOUR SOLAR PANEL SYSTEM</h3>
            <input type="submit" id="btnStartWizard" style="float: right" 
                value="I AM READY, START NOW" />
        </div>
        <div class="clear"></div>
                

        <!-- start: Your System -->
        <div class="grid_12" id="divSelectionTabs">
            <input type="text" id="currentComponent" name="currentComponent" style="display:none;" />
            <table class="your-system">
                <tr>
                    <td id="tdEnergyUserProfile">
                        <h4>Your Details</h4>
                        <p id="selectionUserPostcode" style="display:none;"></p>
                        <p id="selectionAverageConsumption" style="display:none;"></p>
                        <p id="detailsUserAverageConsumption"></p>
                    </td>
                    <td id="tdSolarPanelSelection">
                        <h4>Solar Panel</h4>
                        <p id="selectionSolarPanel" style="display:none;"></p>
                        <p id="selectionNumberOfPanels" style="display:none;"></p>
                        <p id="detailsSolarPanel"></p>
                    </td>
                    <td id="tdInverterSelection">
                        <h4>Inverter</h4>
                        <p id="selectionInverter" style="display:none;"></p>
                        <p id="detailsInverter"></p>
                    </td>
                    <td id="tdBatterySelection">
                        <h4>Battery</h4>
                        <p id="selectionBattery" style="display:none;"></p>
                        <p id="detailsBattery"></p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="clear"></div>
        <!-- end: Your System -->

        <!-- start: title caption -->
        <h2 class="grid_12" id="txtCaptionTitle" style="margin-top:30px; margin-bottom:30px;"></h2>
        <div class="clear"></div>
        <!-- end: title caption -->

        <!-- start: user details -->
        <div class="prefix_3 grid_6 suffix_3" id="divEnergyUserProfile" >
            <p>
            <strong>YOUR POSTCODE:</strong><br />
            <em>Please input your postcode, e.g. 4121</em><br />
            <input type="text"  style="width:150px;" id="txtUserPostcode" value="4000" name="txtUserPostcode"/>
            </p>
            <p>
            
            <strong>QUARTERLY AVERAGE ENERGY CONSUMPTION:</strong><br />
            <em>Must be in kWh (kilowatt-hour). You can find it in your electricity bill, e.g. 1650</em>
            <br />
            <input type="text"  style="width:150px;" id="txtAverageConsumption" value="1650" name="txtAverageConsumption" />
            </p>
            <input type="submit" value="CONTINUE" style="float: right" id="btnSubmitEnergyProfile" />

            
        </div>
        <div class="clear"></div>
        <!-- end: user details -->
        
        <!-- start: filter results area -->
        <div class="grid_3" id="divFilterResults">
            <table style="width: 100%">
                <tr>
                    <th colspan="2">LOCATION</th>
                </tr>
                <tr>
                    <td style="width: 120px;">
                        Postcode
                    </td>
                    <td>
                        <input id="txtPostcode" type="text" name="txtPostcode" />
                    </td>
                </tr>
                <tr>
                    <th colspan="2">PRICE (AUD)</th>
                </tr>
                <tr>
                    <td>
                        Minimum
                    </td>
                    <td>
                        <select id="txtMinimumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum
                    </td>
                    <td>
                        <select id="txtMaximumPrice"></select>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">EFFICIENCY (W) </th>
                </tr>
                <tr>
                    <td>
                        Minimum
                    </td>
                    <td>
                        <select id="txtMinimumEfficiency"></select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Maximum
                    </td>
                    <td>
                        <select id="txtMaximumEfficiency"></select>
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnFilterResults" style="float: right" />
        </div>
        <!-- end: filter results area -->

        <!-- start: wizard selection steps -->
        <div class="grid_9">
            <div id="divSelectSolarPanel">
                <div style="display:none;">
                    <h4>Please select a component from the list below. You can also narrow your results by using the filters on the left.</h4>
                    <p>Don't have that component?<br />
                    <a href="#" id="linkManualInpsut"><strong>Input Details Manually</strong></a> </p>
                </div>
            </div>
            <div id="divSelectInverter">
            </div>
            <div id="divSelectBattery">
            </div>
        </div>
        <div class="clear"></div>
        <!-- end: wizard steps -->

        <!-- start: results selectionpanel -->
        <div id="divResultsPanel" class="grid_12">
            <h3>YOUR RESULTS</h3>
            <table id="wizardResults">
                <tr>
                    <td></td>
                    <td>PER YEAR</td>
                    <td>PER MONTH</td>
                    <td>PER WEEK</td>
                </tr>
                <tr>
                    <td><h4>ELECTRICITY PRODUCTION (kWh)</h4></td>
                    <td><h2 id="electricity_year">NA</h2></td>
                    <td><h2 id="electricity_month">NA</h2></td>
                    <td><h2 id="electricity_week">NA</h2></td>
                </tr>
                <tr>
                    <td><h4>TOTAL COST (AUD)</h4></td>
                    <td><h2 id="totalCost_year">NA</h2></td>
                    <td><h2 id="totalCost_month">NA</h2></td>
                    <td><h2 id="totalCost_week">NA</h2></td>
                </tr>
                <tr>
                    <td><h4>RETURN ON INVESTMENT (%)</h4></td>
                    <td><h2 id="roi_year">NA</h2></td>
                    <td><h2 id="roi_month">NA</h2></td>
                    <td><h2 id="roi_week">NA</h2></td>
                </tr>
                <tr>
                    <td><h4>EXPECTED GOVERNMENT REBATES (AUD)</h4></td>
                    <td><h2 id="governmentRebates_year">NA</h2></td>
                    <td><h2 id="governmentRebates_month">NA</h2></td>
                    <td><h2 id="governmentRebates_week">NA</h2></td>
                </tr>
                <tr style="display:none;">
                    <td><h4>OPTIMAL PANEL ANGLE (DEGREES)</h4></td>
                    <td><h2 id="optimalFacingAngle_summer">NA</h2><br />SUMMER</td>
                    <td><h2 id="optimalFacingAngle_winter">NA</h2><br />WINTER</td>
                </tr>
                
            </table>

        </div>
        <div class="clear"></div>
        <!-- end: results panel -->
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/GlobalVars.js" type="text/javascript"></script>
    <script src="js/orange/HelperFunctions.js" type="text/javascript"></script>
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script src="js/libs/jExpand.js" type="text/javascript"></script>
    <script src="js/orange/Calculate.aspx.js" type="text/javascript"></script>
    <!-- start: steps set up -->
    <script type="text/javascript">
        function resetWizard() {
            //scroll to beggining of the page
            window.scrollTo(0, 0);

            //reset dropdowns
            var helper = new HelperFunctions();
            helper.fillSelect(100, 1000, 100, $('#txtMinimumPrice'));
            helper.fillSelect(100, 1000, 100, $('#txtMaximumPrice'));
            helper.fillSelect(50, 300, 50, $('#txtMinimumEfficiency'));
            helper.fillSelect(50, 300, 50, $('#txtMaximumEfficiency'));

            //reset 'Your System' area
            $('#selectionSolarPanel').text('NOT SELECTED');
            $('#selectionInverter').text('NOT SELECTED');
            $('#selectionBattery').text('NOT SELECTED');
            $('#detailsSolarPanel').text('');
            $('#selectionBattery').text('');
            $('#selectionnverter').text('');
            $('#txtUserPostcode').val('4000');
            $('#txtAverageConsumption').val('1000');


            

            //clear textbox for currentComponent
            $('#currentComponent').val('start');

            //hide and display appropriate tabs
            $('#divWelcomePanel').show();
            $('#divEnergyUserProfile').hide();
            $('#divSelectSolarPanel').hide();
            $('#divSelectInverter').hide();
            $('#divSelectBattery').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').hide();
            $('#divFilterResults').hide();
            $('#divSelectionTabs').hide();

            //styles for "your system" area
            $('#tdEnergyUserProfile').attr('class', '').addClass('not-selected');
            $('#tdSolarPanelSelection').attr('class', '').addClass('not-selected');
            $('#tdInverterSelection').attr('class', '').addClass('not-selected');
            $('#tdBatterySelection').attr('class', '').addClass('not-selected');

        }

        function stepEnergyUserProfile() {
            //hide and display appropriate tabs
            $('#divSelectionTabs').show();
            $('#divWelcomePanel').hide();
            $('#divEnergyUserProfile').show();
            $('#divSelectSolarPanel').hide();
            $('#divSelectInverter').hide();
            $('#divSelectBattery').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').show();
            $('#divFilterResults').hide();

            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divSelectionTabs").offset().top
            }, 2000);

            //reset 'Your System' area
            $('#detailsUserAverageConsumption').text('CURRENTLY SELECTING');
            $('#detailsSolarPanel').text('');
            $('#detailsInverter').text('');
            $('#detailsBattery').text('');




            //set up textbox for currentComponent
            $('#currentComponent').val('panel');

            //set up caption
            $('#txtCaptionTitle').text('STEP 1 OF 4: YOUR LOCATION AND ENERGY CONSUMPTION');

            //styles for "your system" area
            $('#tdEnergyUserProfile').attr('class', '').addClass('selecting');
            $('#tdSolarPanelSelection').attr('class', '').addClass('not-selected');
            $('#tdInverterSelection').attr('class', '').addClass('not-selected');
            $('#tdBatterySelection').attr('class', '').addClass('not-selected');
        }
        

        function stepSolarPanelSelection() {
            //hide and display appropriate tabs
            $('#divSelectionTabs').show();
            $('#divWelcomePanel').hide();
            $('#divEnergyUserProfile').hide();
            $('#divSelectSolarPanel').show();
            $('#divSelectInverter').hide();
            $('#divSelectBattery').hide();
            $('#divResultsPanel').hide();
            $('#txtCaptionTitle').show();
            $('#divFilterResults').show();

            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divSelectionTabs").offset().top
            }, 2000);

            //reset 'Your System' area

            $('#detailsSolarPanel').text('CURRENTLY SELECTING');
            $('#detailsInverter').text('');
            $('#detailsBattery').text('');
            



            //set up textbox for currentComponent
            $('#currentComponent').val('panel');

            //set up caption
            $('#txtCaptionTitle').text('STEP 2 OF 4: SELECT A SOLAR PANEL');

            //styles for "your system" area
            $('#tdEnergyUserProfile').attr('class', '').addClass('selected');
            $('#tdSolarPanelSelection').attr('class','').addClass('selecting');
            $('#tdInverterSelection').attr('class', '').addClass('not-selected');
            $('#tdBatterySelection').attr('class', '').addClass('not-selected');

            
            
        }

        function stepInverterSelection() {
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide('slideRight');
            $('#divSelectionTabs').show('slideLeft');
            $('#divSelectSolarPanel').hide('slideRight');
            $('#divSelectInverter').show('slideLeft');
            $('#divSelectBattery').hide('slideRight');
            $('#divResultsPanel').hide('slideRight');
            $('#txtCaptionTitle').show('slideLeft');
            $('#divFilterResults').show('slideLeft');


            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divSelectionTabs").offset().top
            }, 2000);

            //reset 'Your System' area
            //$('#detailsSolarPanel').text('PLEASE SELECT');
            $('#detailsInverter').text('CURRENTLY SELECTING');
            $('#detailsBattery').text('');

            //set up textbox for currentComponent
            $('#currentComponent').val('inverter');
            
            //set up text
            $('#txtCaptionTitle').text('STEP 3 OF 4: SELECT AN INVERTER');

            //styles for "your system" area
            $('#tdSolarPanelSelection').attr('class', '').addClass('selected');
            $('#tdInverterSelection').attr('class', '').addClass('selecting');
            $('#tdBatterySelection').attr('class', '').addClass('not-selected');
            
        }

        function stepBatterySelection() {
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide('slideRight');
            $('#divSelectionTabs').show('slideLeft');
            $('#divSelectSolarPanel').hide('slideRight');
            $('#divSelectInverter').hide('slideRight');
            $('#divSelectBattery').show('slideLeft');
            $('#divResultsPanel').hide('slideRight');
            $('#txtCaptionTitle').show('slideLeft');
            $('#divFilterResults').show('slideLeft');

            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divSelectionTabs").offset().top
            }, 2000);

            $('#currentComponent').val('battery');

            //reset 'Your System' area
            //$('#detailsSolarPanel').text('PLEASE SELECT');
            //$('#detailsInverter').text('PLEASE SELECT');
            $('#detailsBattery').text('CURRENTLY SELECTING');

            //set up caption
            $('#txtCaptionTitle').text('STEP 4 OF 4: SELECT A BATTERY');

            //styles for "your system" area
            $('#tdSolarPanelSelection').attr('class', '').addClass('selected');
            $('#tdInverterSelection').attr('class', '').addClass('selected');
            $('#tdBatterySelection').attr('class', '').addClass('selecting');

            
        }

        function stepResults() {
            //hide and display appropriate tabs
            $('#divWelcomePanel').hide('slideRight');
            $('#divSelectionTabs').show('slideLeft');
            $('#divSelectSolarPanel').hide('slideRight');
            $('#divSelectInverter').hide('slideRight');
            $('#divSelectBattery').hide('slideRight');
            $('#divResultsPanel').show('slideLeft');
            $('#txtCaptionTitle').hide('slideRight');
            $('#divFilterResults').hide('slideRight');

            //scroll to beggining of the page
            $('html, body').animate({
                scrollTop: $("#divSelectionTabs").offset().top
            }, 2000);

            $('#currentComponent').val('results');

            //styles for "your system" area
            $('#tdSolarPanelSelection').attr('class', '').addClass('selected');
            $('#tdInverterSelection').attr('class', '').addClass('selected');
            $('#tdBatterySelection').attr('class', '').addClass('selected');


        }

        
    </script>
    <!-- end: steps set up -->

    <!-- start: script to initialize form -->
    <script type="text/javascript">
        //create global variables
        var globalVars = new OrangeGlobalVars();

        $(document).ready(function () {
            //reset form
            resetWizard();
            

            //create variables to make subsequent ajax calls
            var urlBuilder;
            var h = new HelperFunctions();
            
            //set variables to fill panel table
            urlBuilder = new UrlBuilder();
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'panel'
            //make ajax call to fill panel table
            h.ajaxCallViaProxy(createPanelTable, genericAjaxErrorHandler, urlBuilder.toString());

            //set variables to fill inverter table
            urlBuilder = new UrlBuilder();
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'inverter'
            //make ajax call to fil inverter table
            h.ajaxCallViaProxy(createInverterTable, genericAjaxErrorHandler, urlBuilder.toString());
            

            //set variables to fill battery table
            urlBuilder = new UrlBuilder();
            urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
            urlBuilder.ComponentName = 'battery'
            //make ajax call to fill battery table
            h.ajaxCallViaProxy(createBatteryTable, genericAjaxErrorHandler, urlBuilder.toString());
            

        });
    </script>
    <!-- end: script to initialize form -->

    <!-- start: generic ajax error handler -->
    <script type="text/javascript">
        function genericAjaxErrorHandler() {
            alert('an error has occurred');
        }
    </script>
    <!-- end: generic ajax error handler -->

    <!-- start: filter results panel -->
    <script type="text/javascript">
        $(document).ready(function () {
            //bind click event
            $('#btnFilterResults').click(function (e) {
                e.preventDefault();

                var h = new HelperFunctions();

                if ($('#currentComponent').val() == 'panel') {
                    //set variables to fill panel table
                    var urlBuilder = new UrlBuilder();
                    urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
                    urlBuilder.ComponentName = 'panel';
                    urlBuilder.Postcode = $('#txtPostcode').val();
                    urlBuilder.MinimumCapacity = $('#txtMinimumEfficiency').val();
                    urlBuilder.MaximumCapacity = $('#txtMaximumEfficiency').val();
                    urlBuilder.MinimumPrice = $('#txtMinimumPrice').val();
                    urlBuilder.MaximumPrice = $('#txtMaximumPrice').val();


                    //make ajax call to fill panel table
                    h.ajaxCallViaProxy(createPanelTable, genericAjaxErrorHandler, urlBuilder.toString());

                }
                else if ($('#currentComponent').val() == 'inverter') {
                    //set variables to fill inverter table
                    var urlBuilder = new UrlBuilder();
                    urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
                    urlBuilder.ComponentName = 'inverter';
                    urlBuilder.Postcode = $('#txtPostcode').val();
                    urlBuilder.MinimumCapacity = $('#txtMinimumEfficiency').val();
                    urlBuilder.MaximumCapacity = $('#txtMaximumEfficiency').val();
                    urlBuilder.MinimumPrice = $('#txtMinimumPrice').val();
                    urlBuilder.MaximumPrice = $('#txtMaximumPrice').val();


                    //make ajax call to fill inverter table
                    h.ajaxCallViaProxy(createInverterTable, genericAjaxErrorHandler, urlBuilder.toString());


                }
                else if ($('#currentComponent').val() == 'battery') {
                    //set variables to fill battery table
                    var urlBuilder = new UrlBuilder();
                    urlBuilder.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
                    urlBuilder.ComponentName = 'battery';
                    urlBuilder.Postcode = $('#txtPostcode').val();
                    urlBuilder.MinimumCapacity = $('#txtMinimumEfficiency').val();
                    urlBuilder.MaximumCapacity = $('#txtMaximumEfficiency').val();
                    urlBuilder.MinimumPrice = $('#txtMinimumPrice').val();
                    urlBuilder.MaximumPrice = $('#txtMaximumPrice').val();


                    //make ajax call to fill battery table
                    h.ajaxCallViaProxy(createBatteryTable, genericAjaxErrorHandler, urlBuilder.toString());


                }
            });

        });
    
    </script>
    <!-- end: filter results panel -->
    
    <!-- start: script to fill  solar panel tables -->
    <script type="text/javascript">
        function createPanelTable(xml)
        {
            //remove any existing panel tables
            $('#tblPanelResults').remove();

            //create table element
            var $table = $('<table>').attr({ 'id': 'tblPanelResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');

            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("panel ").each(function () {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'panel_row_' + id });

                //create html cells and append into row
                $row.append(
                    helper.createCell(
                        '<strong>' + helper.readValueFromXml($xmlRow, 'manufacturer') + '</strong><br />' + helper.readValueFromXml($xmlRow, 'model')
                    )
                );
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for the call to action
                var $callToAction = $('<input>').attr({ 'id': 'panel_id_' + helper.readValueFromXml($xmlRow, 'id'), 'class': 'panel-select', 'type':'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));



                //append all main row to table
                $table.append($row);

                //create row for details elements
                var $rowDetails = $('<tr>').attr('class', 'row-details');
                $rowDetails.append(
                    $('<td>').attr({ 'colspan': '4' }).html(
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'model') + '</h5>' +
                        '<ul>' +
                        '<li>POSTCODE: ' + helper.readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>IDENTIFIER: ' + helper.readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + helper.readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + helper.readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>OPERATING CURRENT: ' + helper.readValueFromXml($xmlRow, 'operatingCurrent') + '</li>' +
                        '<li>WARRANTY: ' + helper.readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>DESCRIPTION: ' + helper.readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>'
                    )
                );

                //append details row to table
                $table.append($rowDetails);

            });


            //append the table into divSelectSolarPanel
            $('#divSelectSolarPanel').append($table);
            $table.jExpand();
        }
        
        
    
    </script>
    <!-- end: script to fill  solar panel tables -->

    <!-- start: script to fill inverter table -->
    <script type="text/javascript">
        function createInverterTable(xml) {
            //remove any existing inverter tables
            $('#tblInverterResults').remove();
            
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblInverterResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every inverter in the xml
            $(xml).find("inverter").each(function () {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'inverter_row_' + id });

                //create html cells and append into row
                $row.append(helper.createCell(
                    '<strong>'+helper.readValueFromXml($xmlRow, 'manufacturer')+'</strong><br />' +helper.readValueFromXml($xmlRow, 'model')
                ));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for call to action
                var $callToAction = $('<input>').attr({ 'id': 'inverter_id_'+helper.readValueFromXml($xmlRow, 'id'), 'class': 'inverter-select', 'type': 'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'name') + '</h5>' +
                        '<ul>' +
                        '<li>POSTCODE: ' + helper.readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>IDENTIFIER: ' + helper.readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + helper.readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + helper.readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>WARRANTY: ' + helper.readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>BATTERY VOLTAGE RANGE: ' + helper.readValueFromXml($xmlRow, 'batteryVoltageRange') + '</li>' +
                        '<li>OUTPUT VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'outputVoltage') + '</li>' +
                        '<li>OUTPUT FREQUENCY: ' + helper.readValueFromXml($xmlRow, 'outputFrequency') + '</li>' +
                        '<li>DESCRIPTION: ' + helper.readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>'
                    )
                );
                $table.append($row);


            });

            //append the table into divSelectInverter
            $('#divSelectInverter').append($table);
            $table.jExpand();
               
        }
    </script>
    <!-- start: script to fill inverter table -->

    <!-- start: script to fill battery table -->
    <script type="text/javascript">
        function createBatteryTable(xml) {
            //remove any existing inverter tables
            $('#tblBatteryResults').remove();

            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblBatteryResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MANUFACTURER / MODEL'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $thead.append($('<td>').text('ACTION'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every battery in the xml
            $(xml).find("battery").each(function () {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'battery_row_' + id });

                //create html cells and append into row
                $row.append(helper.createCell(
                    '<strong>' + helper.readValueFromXml($xmlRow, 'manufacturer') + '</strong><br />' + helper.readValueFromXml($xmlRow, 'model')
                ));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                //create cell for call to action
                var $callToAction = $('<input>').attr({ 'id': 'battery_id_'+helper.readValueFromXml($xmlRow, 'id'), 'class': 'battery-select', 'type': 'submit' }).val('Select');
                $row.append($('<td>').append($callToAction));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
                        '<h5>Additional Information for ' + helper.readValueFromXml($xmlRow, 'name') + '</h5>' +
                        '<ul>' +
                        '<li>IDENTIFIER: ' + helper.readValueFromXml($xmlRow, 'id') + '</li>' +
                        '<li>POSTCODE: ' + helper.readValueFromXml($xmlRow, 'postcode') + '</li>' +
                        '<li>VOLTAGE: ' + helper.readValueFromXml($xmlRow, 'voltage') + '</li>' +
                        '<li>DIMENSIONS: ' + helper.readValueFromXml($xmlRow, 'dimensions') + '</li>' +
                        '<li>EFFIENCY DECREASE: ' + helper.readValueFromXml($xmlRow, 'efficiencyDecrease') + '</li>' +
                        '<li>WARRANTY: ' + helper.readValueFromXml($xmlRow, 'warranty') + '</li>' +
                        '<li>DESCRIPTION: ' + helper.readValueFromXml($xmlRow, 'description') + '</li>' +
                        '</ul>'
                    )
                );
                $table.append($row);


            });

            //append the table into divSelectSolarPanel
            $('#divSelectBattery').append($table);
            $table.jExpand();

        }
    </script>
    <!-- end: script to fill battery table -->
    
    <!-- start: btnStartWizard -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#btnStartWizard').click(function (e) 
            {
                e.preventDefault();
                stepEnergyUserProfile();
            });
        });
    </script>
    <!-- end: btnStartWizard -->

    <!-- start: btnSubmitEnergyProfile -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#btnSubmitEnergyProfile').click(function (e) {
                e.preventDefault();
                $('#detailsUserAverageConsumption').html('POSTCODE: '+ $('#txtUserPostcode').val() +'<br/>ENERGY: '+ $('#txtAverageConsumption').val() +'kWh');
                stepSolarPanelSelection();
            });
        });
    
    </script>
    <!-- end: btnSubmitEnergyProfile -->

    <!-- start: solar panel selection -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#divSelectSolarPanel').on('click', '.panel-select', function (e) {
                e.preventDefault();
                var numberPanels = prompt('Number of Panels', '10');
                $('#selectionNumberOfPanels').text(numberPanels);
                $('#selectionSolarPanel').text($(this).attr('id'));
                $('#detailsSolarPanel').html($(this).parent().prev().prev().prev().html());
                stepInverterSelection();
            });
        });
    </script>
    <!-- end: solar panel selection -->

    <!-- start: inverter selection -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#divSelectInverter').on('click', '.inverter-select', function (e) {
                e.preventDefault();
                $('#selectionInverter').text($(this).attr('id'));
                $('#detailsInverter').html($(this).parent().prev().prev().prev().html());
                stepBatterySelection();
            });
        });
    </script>
    <!-- end: inverter selection -->

    <!-- start: battery selection -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#divSelectBattery').on('click', '.battery-select', function (e) {
                e.preventDefault();
                $('#selectionBattery').text($(this).attr('id'));
                $('#detailsBattery').html($(this).parent().prev().prev().prev().html());
                getResults();
                stepResults();
            });
        });

        
    </script>
    <!-- end: battery selection -->

   

</asp:Content>