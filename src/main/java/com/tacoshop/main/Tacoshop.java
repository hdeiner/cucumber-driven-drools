package com.tacoshop.main;

import com.tacoshop.model.Purchase;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import java.math.BigDecimal;

class Tacoshop {
    public static void main(String[] args) {

        StatefulKnowledgeSession session;
        Purchase purchase;

        session = null;

        try {

            KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            builder.add(ResourceFactory.newClassPathResource("com/tacoshop/rules/discountRules.drl"), ResourceType.DRL);
            if (builder.hasErrors()) {
                throw new RuntimeException(builder.getErrors().toString());
            }

            KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
            knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

            session = knowledgeBase.newStatefulKnowledgeSession();

        } catch(Throwable t) {
            t.printStackTrace();
        }

        System.out.println("Order of $16 consisting of 4 tacos with drinks");
        purchase = new Purchase(new BigDecimal("16.00"), 4, true);
        FactHandle purchaseFact = session.insert(purchase);
        session.fireAllRules();
        System.out.println("Discount of " + purchase.getDiscount() + "%\n");

        session.dispose();
    }
}