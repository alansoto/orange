function Graph(canvas) {
	this.canvas = canvas;
	this.data = [[]];
	this.opt = {
		series: {
			color: "#1EB414",
			lines: {show: true},
			points: {show: true}
		},
//		legend: {show: false},
		xaxis: {
			tickSize: 2,
			tickDecimals: 0,
//			mode: 'time',
//			timeformat: '%y-%0m-%0d %H:%M:%S',
//			minTickSize: [30, 'second'],
		},
		yaxis: {
			min: 0,
//			max: 100,
//			panRange: false
		},
		points: {radius: 1},
//		shadowSize: 2,
//		selection: {mode: "x"},
		grid: {hoverable: true, clickable: true},
//		zoom: {interactive: true},
//		pan: {
//			interactive: true,
//			mode: "x"
//		},
	};
	this.plot = null;
}

Graph.prototype = {

// Set data from string which each value saparated by ;
setData: function(str, startYear) {
	var vals = str.split(";");
	var series = [];
	if (!startYear) {
		startYear = 1;
	}
	for (var i in vals) {
		series.push([startYear, parseInt(vals[i], 10)]);
		++startYear;
	}
	this.setSeries(0, series);
},

setSeries: function(idx, series) {
	this.data[idx] = series;
},

draw: function() {
	this.plot = $.plot(this.canvas, this.data, this.opt);
},

// Requires:
// flot.selection.js, flot.navigate.js
drawOverview: function(canvas) {
	var opt = $.extend({}, this.opt);
	opt.lines = { show: true, lineWidth: 2 };
	opt.series.shadowSize = 0;
//	opt.series.xaxis = { ticks: [], mode: "time" };
//	opt.series.yaxis = { ticks: [], min: 0, autoscaleMargin: 0.1 };
	opt.points = { radius: 0.1 };
//	opt.selection = { mode: "x" };
//	delete(opt.pan);
//	delete(opt.zoom);
	
	var overview = $.plot(canvas, this.data, opt);
	var self = this;

	canvas.bind("plotselected", function(e, ranges) {
		// zooming
		self.opt.xaxis.min = ranges.xaxis.from;
		self.opt.xaxis.max = ranges.xaxis.to;
		self.draw();
	});

	this.canvas.bind("plotpan", function(e, plot) {
        	var axes = plot.getAxes();
		var ranges = overview.getSelection();
		if (ranges) {
			ranges.xaxis.from = axes.xaxis.min;
			ranges.xaxis.to = axes.xaxis.max;
			overview.setSelection(ranges, true);
		}
	});

},

enableTooltip: function() {
	var prevPoint = null;
	var tooltip = null;
	var self = this;
	this.canvas.bind('plothover', function(evt, pos, item) {
		if (item) {
			if (prevPoint != item.dataIndex) {
				// get title and time
				if (tooltip == null) {
					tooltip = $('<div />').addClass('tooltip').appendTo('body');
				}
				tooltip.text(self.getTooltip(item));
				tooltip.css({
					position: "absolute",
					left: item.pageX + 5,
					top: item.pageY - 20
				});
				prevPoint = item.dataIndex;
			}
		} else {
			if (tooltip != null) {
				tooltip.remove();
				tooltip = null;
			}
			prevPoint = null;
		}
	});
},

getTooltip: function(item) {
	var text = item.datapoint[0].toString();
	text += ": " + item.datapoint[1];
	return text;
}

};

Graph.genSeries = function(duration) {
	var series = [], i, d, prev, val;

	for (i = 0; i < duration; ++i) {
		prev = (series.length) ? series[series.length - 1][1] : 50;
		val = Math.round(prev + Math.random() * 20) - 10;
		d = [
			2010 + i,
			(val < 0) ? 0 : (val > 100) ? 100 : val
		];
		series.push(d);

	}
	return series;
};

