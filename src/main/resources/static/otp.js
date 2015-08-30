var load = null;

(function() {
	var _interval;

	load = function(json) {
		var i = 0;
		$.each(json, function(key, value) {
			renderContainer(key, value, i % 3);
			i++;
		});

		setTimeout(showPasswords, 1);
	}

	var renderContainer = function(providerName, displayName, columnIndex) {
		var column = $('#col_' + columnIndex);
		var coldom = column[0];

		var container = $('<div/>', {
			'class' : 'panel panel-default',
			'id' : 'container_' + providerName
		}).appendTo(column);

		var header = $('<div/>', {
			'class' : 'panel-heading',
			'id' : 'header_' + providerName
		}).appendTo(container);

		var title = $('<h3/>', {
			'class' : 'panel-title',
			'id' : 'title_' + providerName
		}).text(displayName).appendTo(header);

		var body = $('<div/>', {
			'class' : 'panel-body',
			'id' : 'body_' + providerName
		}).appendTo(container);

		var pwHolder = $('<h3/>', {
			'id' : 'pw_' + providerName
		}).appendTo(body);

		var barHolder = $('<div/>', {
			'class' : 'progress'
		}).appendTo(body);

		var progressBar = $('<div/>', {
			'class' : 'pbar progress-bar progress-bar-striped active',
			'id' : 'progress_' + providerName,
			'aria-valuemin' : '0',
			'aria-valuemax' : '29'
		}).appendTo(barHolder);
	};

	var showPasswords = function() {
		$.getJSON('/keys').done(function(data) {
			$.each(data.providers, function(provider, passwordData) {
				if (_interval) {
					clearInterval(_interval);
					_interval = null;
				}

				$('#pw_' + provider).text(passwordData.key);
			});

			_interval = setInterval(renderProgressBars, 1000);
			renderProgressBars();
		});
	};

	var renderProgressBars = function() {
		var now = Date.now() / 1000;

		var timeElapsed = Math.floor(now % 30);
		var width = timeElapsed * (100.0 / 29.0);

		if (timeElapsed == 0) {
			setTimeout(showPasswords, 1);
			return
		}
		
		var color = 'success';
		if (timeElapsed >= 20 && timeElapsed < 25) {
			color = 'warning';
		} else if (timeElapsed >= 25) {
			color = 'danger';
		}

		$('.progress-bar').each(function(index) {
			$(this).removeClass('progress-bar-success progress-bar-warning progress-bar-danger');
			$(this).addClass('progress-bar-' + color);
			$(this).attr('aria-valuenow', timeElapsed).width(
					width + '%');
		});

	};
})();