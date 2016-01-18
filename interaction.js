var capital_letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
var lowercase_letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
var digits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
var days_of_week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var caps_different = false;

var current_color;
var item = 0;
var section = 0;
var grapheme_queue = [];
var input_text = "";


$('#submit-types-survey').click(function(){
	if($('#letters').is(':checked')){
		grapheme_queue = grapheme_queue.concat(capital_letters);
	}
	if($('#capital-different').is(':checked')){
		grapheme_queue = grapheme_queue.concat(lowercase_letters);
		caps_different = true;
	}
	else{
		caps_different = false;
	}
	if($('#digits').is(':checked')){
		grapheme_queue = grapheme_queue.concat(digits);
	}
	if($('#days-of-week').is(':checked')){
		grapheme_queue = grapheme_queue.concat(days_of_week);
	}
	if($('#months').is(':checked')){
		grapheme_queue = grapheme_queue.concat(months);
	}
	other_vals = $('#other').val().split(' ');
	grapheme_queue = grapheme_queue.concat(other_vals);

	for(var i = 0; i < grapheme_queue.length; i++){
		if(localStorage.getItem(grapheme_queue[i]) == null){
			localStorage.setItem([grapheme_queue[i]], '#000000');
		}
	}
	$('#types-survey').hide();
	$('#processing').show();
	$('#current-grapheme').show();
	$('#grapheme').text(grapheme_queue[item]);
	$('#prev').show();
	update_grapheme_color(localStorage.getItem(grapheme_queue[item]));
	section++;
})

function update_grapheme_color(color_val){
	$('#grapheme').css("color", color_val);
	current_color = color_val;
	localStorage.setItem(grapheme_queue[item], current_color);
}

$('#next-grapheme').click(function(){
	localStorage.setItem(grapheme_queue[item], current_color);
	item = Math.min(grapheme_queue.length-1, item + 1);
	$('#grapheme').text(grapheme_queue[item]);
	update_grapheme_color(localStorage.getItem(grapheme_queue[item]));
})

$('#prev-grapheme').click(function(){
	localStorage.setItem(grapheme_queue[item], current_color);
	item = Math.max(0, item - 1);
	$('#grapheme').text(grapheme_queue[item]);
	update_grapheme_color(localStorage.getItem(grapheme_queue[item]));
})

$('#color-input-done').click(function(){
	$('#processing').hide();
	$('#current-grapheme').hide();
	$('#input-section').show();
	section++;
})

$('#text-input-done').click(function(){
	$('#input-section').hide();
	$('#output-section').show();
	input_text = $('#input').val();
	$('#output').html(produce_output_text());
	section++;
})

$('#prev').click(function(){
	if(section == 1){
		grapheme_queue = [];
		item = 0;
		$('#prev').hide();
		$('#types-survey').show();
		$('#processing').hide();
		$('#current-grapheme').hide();
	}
	else if(section == 2){
		$('#processing').show();
		$('#current-grapheme').show();
		$('#input-section').hide();
	}
	else if(section == 3){
		$('#input-section').show();
		$('#output-section').hide();
	}
	section--;
})

function produce_output_text(){
	var string = "";
	var word_positions = {};
	for(item of grapheme_queue){
		if(item.length > 1){
			var index = 0;
			while(index != -1){
				index = input_text.indexOf(item, index+1);
				word_positions[index] = item;
			}
		}
	}
	skip_until = null;
	for(var i = 0; i < input_text.length; i++){
		if(skip_until != null && i < skip_until){
			continue;
		}
		else if(i in word_positions){
			word = word_positions[i];
			skip_until = i+word.length;
			string += '<font color="' + localStorage.getItem(word) + '">' + word + '</font>';
		}
		else{
			letter = input_text[i];
			if(letter == '\n'){
				string += '<br>';
			}
			if(!caps_different){
				var upper = letter.toUpperCase();
				var color = localStorage.getItem(upper);
			}
			else{
				var color = localStorage.getItem(upper);
			}
			if(color == null) color = '#000000';
			string += '<font color="' + color + '">' + letter + '</font>';
		}
	}
	return string;


}

