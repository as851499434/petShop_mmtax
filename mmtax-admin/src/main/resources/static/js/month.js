$.fn.monthpicker = function(options) {
    console.log(111)
    // 获取当前年份
    var now = new Date().getFullYear(),
        _whYears = [];
    for (var i = now - 20; i < now + 50; i++) {
        _whYears.push(i);
    }
    options.years = _whYears;
    // 获取当前月份
    var nowMonth = new Date().getMonth() + 1;
    // 月份所有按钮
    var allButtons = [];

    var months = options.months || ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],

        Monthpicker = function(el) {
            this._el = $(el);
            this._init();
            this._render();
            this._renderYears();
            this._renderMonths();
            this._bind();
            allButtons = $('.monthpicker table button');
        };

    Monthpicker.prototype = {
        destroy: function() {
            this._el.off('click');
            this._yearsSelect.off('click');
            this._container.off('click');
            $(document).off('click', $.proxy(this._hide, this));
            this._container.remove();
        },

        _init: function() {
            this._el.html(options.years[0] + '-' + months[0]);
            this._el.data('monthpicker', this);
        },

        _bind: function() {
            this._el.on('click', $.proxy(this._show, this));
            $(document).on('click', $.proxy(this._hide, this));
            this._yearsSelect.on('click', function(e) { e.stopPropagation(); });
            $('#whSelect').change(function() {
                var selectYear = $(this).children('option:selected').val();
                $.each(allButtons, function(i, button) {
                    if (selectYear == now) {
                        if (nowMonth - 1 == i) {
                            button.setAttribute('class', 'nowMonth');
                        } else {
                            button.setAttribute('class', '');
                        }
                    } else {
                        button.setAttribute('class', '');
                    }
                });
            })
            this._container.on('click', 'button', $.proxy(this._selectMonth, this));
        },

        _show: function(e) {
            e.preventDefault();
            e.stopPropagation();
            this._container.css('display', 'inline-block');
            var currMonth = this._el.attr("month");
            if (currMonth) {
                $.each(allButtons, function(i, button) {
                    if (button.innerHTML == currMonth) {
                        button.setAttribute('class', 'curr');
                    } else {
                        button.removeAttribute('class', 'curr');
                    }
                });
            }
            var selectYear = $("#whSelect").children('option:selected').val();
            $.each(allButtons, function(i, button) {
                if (now == selectYear) {
                    if (nowMonth - 1 == i) {
                        button.classList.add("nowMonth");
                    }
                } else {
                    button.classList.remove("nowMonth");
                }
            });
        },

        _hide: function() {
            this._container.css('display', 'none');
        },

        _selectMonth: function(e) {
            var monthIndex = $(e.target).data('value'),
                month = months[monthIndex],
                year = this._yearsSelect.val();
            if (this._el.attr('type') == "text") {
                this._el.attr("value", year + '-' + month);
            } else {
                this._el.html(year + '-' + month);
            }
            if (options.onMonthSelect) {
                this._el.attr("month", month);
                options.onMonthSelect(monthIndex, year);
            }
        },

        _render: function() {
            var boxOffset = this._el.offset(),
                cssOptions = {
                    display: 'none',
                    position: 'absolute',
                    top: boxOffset.top + this._el.height() + 15,
                    left: boxOffset.left
                };
            var _dowHeight = $(document).height() - (cssOptions.top);
            if (_dowHeight < 165) { // 165为月份插件的高度
                cssOptions.top = boxOffset.top - 170;
            }

            this._id = (new Date).valueOf();
            this._container = $('<div class="monthpicker" id="monthpicker-' + this._id + '">')
                .css(cssOptions)
                .appendTo($('body'));
        },

        _renderYears: function() {
            var markup = $.map(options.years, function (year) {
                if (options.selectYears) {
                    if (options.selectYears == year) {
                        return '<option selected>' + options.selectYears + '</option>';
                    }
                } else {
                    if (now == year) {
                        return '<option selected>' + year + '</option>';
                    }
                }
                return '<option>' + year + '</option>';
            });
            var yearsWrap = $('<div class="years">').appendTo(this._container);
            this._yearsSelect = $('<select id="whSelect">').html(markup.join('')).appendTo(yearsWrap);
        },

        _renderMonths: function() {
            var markup = ['<table>', '<tr>'];
            $.each(months, function(i, month) {
                if (i > 0 && i % 4 === 0) {
                    markup.push('</tr>');
                    markup.push('<tr>');
                }
                markup.push('<td><button data-value="' + i + '">' + month + '</button></td>');
            });
            markup.push('</tr>');
            markup.push('</table>');
            this._container.append(markup.join(''));
        }
    };

    var methods = {
        destroy: function() {
            var monthpicker = this.data('monthpicker');
            if (monthpicker) monthpicker.destroy();
            return this;
        }
    }

    if (methods[options]) {
        return methods[options].apply(this, Array.prototype.slice.call(arguments, 1));
    } else if (typeof options === 'object' || !options) {
        return this.each(function() {
            return new Monthpicker(this);
        });
    } else {
        $.error('Method ' + options + ' does not exist on monthpicker');
    }
}