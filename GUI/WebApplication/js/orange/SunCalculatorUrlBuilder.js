/* Author:ALAN
url builder for connection with google apps
*/
function UrlBuilder()
{
    this.GoogleAppsEngineBaseUrl = "";
    this.ComponentName = "";
    this.MinimumPrice = "";
    this.MaximumPrice = "";
    this.MinimumCapacity = "";
    this.MaximumCapacity = "";
    this.Postcode = "";
    this.Operation = "";
    this.InverterId = "";
    this.BatteryId = "";
    this.PanelId = "";
    this.SystemCost = "";
    this.InverterEfficiency = "";
    this.PanelEfficiency = "";
    this.PanelOutput = "";
    this.PanelCount = "";
    this.EnergyConsumption = "";


    this.toString = function () {
        var completeUrl = "";

        if (this.GoogleAppsEngineBaseUrl != "") {
            var baseUrl = "";
            var parameters = "";

            baseUrl += this.GoogleAppsEngineBaseUrl;

            if (this.ComponentName != "") {
                baseUrl += "/" + this.ComponentName;
            }

            if (this.Operation != "") {
                baseUrl += "/" + this.Operation;
            }

            //start search parameters
            if (this.MinimumPrice != "" || this.MaximumPrice != "") {

                var min = 0; var max = 0;
                if (this.MinimumPrice <= this.MaximumPrice) {
                    min = this.MinimumPrice;
                    max = this.MaximumPrice;
                }
                else {
                    min = this.MaximumPrice;
                    max = this.MinimumPrice;
                }

                parameters += "&priceMin=" + min + "&priceMax=" + max;
            }

            if (this.Postcode != "") {
                parameters += "&postcode=" + this.Postcode;
            }

            if (this.MinimumCapacity != "" || this.MaximumCapacity != "") {
                var min = 0; var max = 0;
                if (this.MinimumCapacity <= this.MaximumCapacity) {
                    min = this.MinimumCapacity;
                    max = this.MaximumCapacity;
                }
                else {
                    min = this.MaximumCapacity;
                    max = this.MinimumCapacity;
                }

                parameters += "&capacityMin=" + min + "&capacityMax=" + max;
            }

            //start calculations of efficiency parameters
            if (this.PanelId != "" && this.InverterId != "" && this.BatteryId != "") {
                parameters += "&inverterId=" + this.InverterId + "&batteryId=" + this.BatteryId + "&panelId=" + this.PanelId + "&energyConsumption=" + this.EnergyConsumption;
                if (this.PanelCount != "") {
                    parameters += "&panelCount=" + this.PanelCount;
                }
                else {
                    parameters += "&panelCount=1";
                }
            }
            else {
                if (this.SystemCost != "") {
                    parameters += "&systemCost=" + this.SystemCost + "&inverterEfficiency=" + this.InverterEfficiency + "&panelEfficiency=" + this.PanelEfficiency + "&panelOutput=" + this.PanelOutput + "&energyConsumption=" + this.EnergyConsumption ;
                }
            }




            //building complete URL
            if (parameters.indexOf("&") == 0) {
                parameters = parameters.replace(parameters.charAt(0), "");
            }


            completeUrl = baseUrl + "?" + parameters;

        }

        return completeUrl;
    }


}
