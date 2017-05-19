package com.abubusoft.kripton.degusta;

import java.io.File;
import java.io.FileOutputStream;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.degusta.model.Attribute;
import com.abubusoft.kripton.degusta.model.Scheda;
import com.abubusoft.kripton.degusta.model.Section;
import com.abubusoft.kripton.degusta.model.Translation;

public class Main {

	public static void main(String[] args) throws Exception {
		Scheda scheda = new Scheda();
		scheda.name = new Translation("scheda degustazione", "scheda degustazione", "scheda degustazione");
		scheda.version=1;

		{
			Section section = new Section("visivo","esame visivo", "examen visuel", "visual examination");
			scheda.sections.add(section);

			{				
				Attribute attribute = new Attribute("limpidezza", "limpidité", "limpidity");
				attribute.values.add(new Translation("velato", "voilè", "veiled"));
				attribute.values.add(new Translation("abbastanza limpido", "assez limpide", "quite limpid"));
				attribute.values.add(new Translation("limpido", "limpide", "limpid"));
				attribute.values.add(new Translation("cristallino", "cristallin", "crystal clear"));
				attribute.values.add(new Translation("brillante", "brillant", "brillant"));
				
				section.attributes.add(attribute);
			}
			
			{				
				Attribute attribute = new Attribute("colore", "couler", "colour");
				attribute.values.add(new Translation("giallo verdolino", "jaune verte pâle", "greenish yellow"));
				attribute.values.add(new Translation("giallo paglierino", "jaune paillé", "straw yellow"));
				attribute.values.add(new Translation("giallo dorato", "jaune doré", "golden yello"));
				attribute.values.add(new Translation("giallo ambrato", "jaune ambré", "amber"));
				attribute.values.add(new Translation("rosa tenue", "rose faible", "soft rosé"));
				attribute.values.add(new Translation("rosa cerasuolo", "rose cerise", "cherry red"));
				attribute.values.add(new Translation("rosa chiaretto", "rose clairet", "dark rosé"));				
				attribute.values.add(new Translation("rosso porpora", "rouge pourpre", "purple red"));
				attribute.values.add(new Translation("rosso rubino", "rouge rubis", "ruby"));
				attribute.values.add(new Translation("rosso granato", "rouge grenat", "garnet"));
				attribute.values.add(new Translation("rosso aranciato", "rouge orangé", "orange red"));
								
				section.attributes.add(attribute);
			}
			
			{				
				Attribute attribute = new Attribute("consistenza", "consistance", "consistency");
				attribute.values.add(new Translation("fluido","fluide","flowing"));
				attribute.values.add(new Translation("poco consistente","peu consistant","scarcely consistent"));
				attribute.values.add(new Translation("abbastanza consistente","assez consistant","quite consistent"));
				attribute.values.add(new Translation("viscoso","visqueux","oily"));				
								
				section.attributes.add(attribute);
			}
			
			{				
				Attribute attribute = new Attribute("granaBollicine", "grana bollicine", "grain des bulles", "size of bubbles");
				attribute.values.add(new Translation("grossolane","grossières","large"));
				attribute.values.add(new Translation("abbastanza fini","assez fine","quite fine"));
				attribute.values.add(new Translation("fini","fines","fine"));							
								
				section.attributes.add(attribute);
			}
			
			{				
				Attribute attribute = new Attribute("numeroBollicine", "numero bollicine", "nombre des bulles", "number of bubbles");
				attribute.values.add(new Translation("scarse","peu nombreuses","very few"));
				attribute.values.add(new Translation("abbastanza numerose","assez nombreuses","quite numerous"));
				attribute.values.add(new Translation("numerose","nombreuses","numerous"));							
								
				section.attributes.add(attribute);
			}
			
			{				
				Attribute attribute = new Attribute("persistenzaPerlage", "persistenza del perlage", "persistance du perlage", "persistence of perlage");
				attribute.values.add(new Translation("evanescenti","évanescentes","fading"));
				attribute.values.add(new Translation("abbastanza persistenti","assez persistantes","quite persistent"));
				attribute.values.add(new Translation("persistenti","persistantes","persistent"));							
								
				section.attributes.add(attribute);
			}

		}
	}
}


