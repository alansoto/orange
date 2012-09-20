<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
      
    <div class="container_12 content">
        <div class="grid_12" style="margin-bottom:40px;">
            <h1 id="componentName">FIND SOLAR PANELS</h1>
            <input type="text" id="txtComponentName" style="display:none" />
        </div>
        <div class="clear">
        </div>
        <div class="grid_3">
            <table style="width: 100%">
                <tr><th colspan="2">LOCATION</th>
                </tr>
                <tr>
                    <td  style="width:120px;">
                        Postcode
                    </td>
                    <td>
                        <input id="txtPostcode" type="text" />
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
                    <th colspan="2">
                        EFFICIENCY (W)
                    </th>
                </tr>
                <tr>
                    <td>Minimum</td>
                    <td>
                        <select id="txtMinimumEfficiency"></select>
                    </td>
                </tr>
                <tr>
                    <td>Maximum</td>
                    <td>
                        <select id="txtMaximumEfficiency"></select>
                    </td>
                </tr>
            </table>
            <input type="submit" id="btnSubmit" style="float: right" />
        </div>
        <div class="grid_9">
            <div id="divLoading" style="display:none; text-align:center;">
                <h5>PROCESSING...</h5>
                <img src="images/ajax-loader.gif" />
            </div>
            <div id="divNoResults">
                <h2>Please use the options on the left to search for components</h2>
            </div>
            <div id="divResults" style="display: none;">
                <h3>YOUR RESULTS:</h3>
            </div>
        </div>
        <div class="clear">
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolderJquery" runat="Server">
    <script src="js/orange/SunCalculatorUrlBuilder.js" type="text/javascript"></script>
    <script src="js/libs/jExpand.js" type="text/javascript"></script>
    <script src="js/orange/HelperFunctions.js" type="text/javascript"></script>
    <script src="js/orange/GlobalVars.js" type="text/javascript"></script>
    
    <!-- start: script to make ajax calls and deal with form -->
    <script type="text/javascript">
        
        $(document).ready(function ()
        {
            var globalVars = new OrangeGlobalVars();

            $('#btnSubmit').click(function (e)
            {
                e.preventDefault();

                //check which component are we working with
                var componentName = $('#txtComponentName').val();

                //build the url to connect with servlet
                var url = new UrlBuilder();
                url.GoogleAppsEngineBaseUrl = globalVars.GoogleAppsEngineBaseUrl;
                url.ComponentName = componentName;
                url.Postcode = $('#txtPostcode').val();
                url.MinimumPrice = $('#txtMinimumPrice').val();
                url.MaximumPrice = $('#txtMaximumPrice').val();
                url.MinimumCapacity = $('#txtMinimumEfficiency').val();
                url.MaximumCapacity = $('#txtMaximumEfficiency').val();

                //create a dummy url
                //url = new UrlBuilder();
                //url.GoogleAppsEngineBaseUrl = "http://solarpowercalc.appspot.com/panel";

                //hide or show a 'please wait' message
                $('#divLoading').toggle();


                //make ajax call
                $.ajax({
                    type: 'POST',
                    url: 'proxy.aspx',
                    dataType: 'xml',
                    data: { servletCallUrl: url.toString() }
                }).done(
                bindXmlToTable
                ).fail(
                errorWhileRetrievingUrl
                );

            });


        });

     

        function bindXmlToTable(xml)
        {
            //remove any existing results table, if exists
            $('#tblResults').remove();
                       
            var componentName = $('#txtComponentName').val();
            var $table;

            if (componentName == 'panel')
            {
                $table = createPanelTable(xml);
            }
            else if (componentName == 'inverter')
            {
                $table = createInverterTable(xml);
            }
            else if (componentName == 'battery')
            {
                $table = createBatteryTable(xml);
            }

            //append the table into divResults
            $('#divResults').append($table);
            $table.jExpand();

            //hide and show respective divs
            $('#divLoading').toggle();
            $('#divResults').slideDown();
            $('#divNoResults').hide();

        }

        function errorWhileRetrievingUrl(errorInfo)
        {
            alert("there was an error trying to retrieve the url: " + url.toString());
        }


        
    </script>
    <!-- end: script to make ajax calls and deal with form -->
    
    <!-- start: create a solar panel table element from xml data -->
    <script type="text/javascript">
        
        function createPanelTable(xml)
        {
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("panel").each(function ()
            {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'model')));
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'manufacturer')));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

                $table.append($row);

                //create row for details elements
                $row = $('<tr>').attr('class', 'row-details');
                $row.append(
                    $('<td>').attr({ 'colspan': '4' }).html
                    (
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
                $table.append($row);


            });

            return $table;
        }
    </script>
    <!-- end: create a solar panel table element from xml data -->

    
    <!-- start: create a inverter table element from xml data-->
    <script type="text/javascript">
        
        function createInverterTable(xml)
        {
            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("inverter").each(function ()
            {
                var helper = new HelperFunctions();

                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'model')));
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'manufacturer')));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

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

            return $table;
        }
    </script>
    <!-- end: create a inverter table element from xml data-->

    
    <!-- start: create a battery table element from xml data-->
    <script type="text/javascript">
        function createBatteryTable(xml)
        {
            var helper = new HelperFunctions();

            //create table element and assign basic attributes 
            var $table = $('<table>').attr({ 'id': 'tblResults' });

            //create header row
            var $theader = $('<thead>');

            //assign column names into header
            $thead = $('<tr>');
            $thead.append($('<td>').text('MODEL'));
            $thead.append($('<td>').text('MANUFACTURER'));
            $thead.append($('<td>').text('PRICE (AUD)'));
            $thead.append($('<td>').text('CAPACITY (W)'));
            $theader.append($thead);

            //append headers into table
            $table.append($theader);

            //append body into table
            $table.append($('<tbody>'));

            var id = 0;
            //iterating through every panel in the xml
            $(xml).find("battery").each(function ()
            {
                //increment for id
                id = id + 1;

                //put the current xml row into memory
                $xmlRow = $(this);

                //create row element for main elements
                var $row = $('<tr>').attr({ 'class': 'row-main', 'id': 'row_' + id });

                //create html cell and append into row
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'model')));
                $row.append(helper.createCell(helper.readValueFromXml($xmlRow, 'manufacturer')));
                var price = helper.readValueFromXml($xmlRow, 'price')
                $row.append(helper.createCell(helper.roundNumber(price, 2)));
                var capacity = helper.readValueFromXml($xmlRow, 'capacity')
                $row.append(helper.createCell(helper.roundNumber(capacity, 2)));

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

            return $table;
        }
    </script>
    <!-- end: create a battery table element from xml data-->

    
    <!-- start: script to deal with link names-->
    <script type="text/javascript">
        
        $(document).ready(function ()
        {
            resetForm('panel', 'SOLAR PANELS');
            
            $('#linkFindBatteries').click(function (e)
            {
                e.preventDefault();
                resetForm('battery', 'BATTERIES');
            });

            $('#linkFindInverters').click(function (e)
            {
                e.preventDefault();
                resetForm('inverter', 'INVERTERS');
            });
            $('#linkFindSolarPanels').click(function (e)
            {
                e.preventDefault();
                resetForm('panel', 'SOLAR PANELS');
            });

        });

        function resetForm(componentName,labelName)
        {
            //hide divResults
            $('#divNoResults').show();
            $('#divResults').hide();
            $('#componentName').text('FIND ' + labelName);
            $('#btnSubmit').val('FIND ' + labelName);

            //reset dropdowns
            var helper = new HelperFunctions();
            helper.fillSelect(100, 1000, 100, $('#txtMinimumPrice'));
            helper.fillSelect(100, 1000, 100, $('#txtMaximumPrice'));
            helper.fillSelect(50, 300, 50, $('#txtMinimumEfficiency'));
            helper.fillSelect(50, 300, 50, $('#txtMaximumEfficiency'));

            //set up componentName
            $('#txtComponentName').val(componentName);

        }


        
    </script>
    <!-- end: script to deal with link names-->
  
</asp:Content>
