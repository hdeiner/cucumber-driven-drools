package com.tacoshop;

import com.tacoshop.model.Purchase;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Test file allowing execution and assertions of our discount rules
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class RuleTest {
	
	/**
	 * simple runner test method that exercises each of our rules
	 */
	@Test
	public void testRules() {
		
		// instantiate a null session for final disposal check
		StatefulKnowledgeSession session = null;
		
		try {
			
			// seed a builder with our rules file from classpath
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("com/tacoshop/rules/discountRules.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
			    throw new RuntimeException(builder.getErrors().toString());
			}
	
			// create a knowledgeBase from our builder packages
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
			
			// initialize a session for usage
			session = knowledgeBase.newStatefulKnowledgeSession();
	
			// purchase > $15 and <= $25
			Purchase firstPurchase = new Purchase(new BigDecimal("16"), 1, false);
			FactHandle purchaseFact = session.insert(firstPurchase);
	        session.fireAllRules();
	        System.out.println("----------------");
	        
	        // purchase > $25
	        firstPurchase = new Purchase(new BigDecimal("26"), 1, false);
	        session.update(purchaseFact, firstPurchase);
	        session.fireAllRules();
	        System.out.println("----------------");
	        
	        // combo purchase containing 3 tacos and a drink
	        firstPurchase = new Purchase(new BigDecimal("26"), 3, true);
	        session.update(purchaseFact, firstPurchase);
	        session.fireAllRules();
	        
		} catch(Throwable t) {
            t.printStackTrace();
        } finally {
    		// if we still have a session at this point, we need to clean it up
        	if (session != null) { 
        		session.dispose();
        	}
        }
	}
}