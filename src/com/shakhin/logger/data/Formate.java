package com.shakhin.logger.data;

public enum Formate {
	TXT,XML,HTML;
	public static Formate toFormate(String formate){
		switch(formate){
			case "TXT" : return Formate.TXT;
			case "XML" : return Formate.XML;
			case "HTML" : return Formate.HTML;
			default : return Formate.TXT;
			}
		}
}
