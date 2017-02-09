package com.tacoshop.test.controller;

import com.tacoshop.model.Purchase;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CucumberStepDefs  {

    private static StatefulKnowledgeSession session;
    private static Purchase purchase;

    @When("^I hear \"([^\"]*)\"$")
    public void i_hear(String arg1) throws Throwable {
    }

    @Then("^I should say \"([^\"]*)\"$")
    public void i_should_say(String arg1) throws Throwable {
    }

    @When("^my purchase is for \"([^\"]*)\" dollars$")
    public void my_purchase_is_for_dollars(String orderDollars) throws Throwable {

        session = null;

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

            purchase = new Purchase();
            purchase.setTotal(new BigDecimal(orderDollars));

        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @When("^my purchase \"([^\"]*)\" has  drinks$")
    public void my_purchase_has_drinks(boolean wereDrinksInOrder) throws Throwable {
        purchase.setDrinkIncluded(wereDrinksInOrder);
    }

    @When("^my purchase has \"([^\"]*)\" tacos$")
    public void my_purchase_has_tacos(int tacoCount) throws Throwable {
        purchase.setTacoCount(tacoCount);
    }

    @Then("^I should get a \"([^\"]*)\" percent discount$")
    public void i_should_get_a_percent_discount(String discountPercent) throws Throwable {
        FactHandle purchaseFact = session.insert(purchase);
        session.fireAllRules();
        session.dispose();
        assertThat(purchase.getDiscount(), is(new BigDecimal(discountPercent)));
    }


}
