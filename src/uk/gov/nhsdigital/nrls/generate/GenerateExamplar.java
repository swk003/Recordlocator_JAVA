package uk.gov.nhsdigital.nrls.generate;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.Parameters.Parameter;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.model.dstu2.resource.Composition;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.narrative.BaseThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.CustomThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.parser.IParser;


public class GenerateExamplar {

	public static void main (String[] args) {

		// create a context for DSTU2
		FhirContext ctx = FhirContext.forDstu2();
		
		DocumentReference docref = CreateXML.createDocumentReference();
		// We can now use a parser to encode this resource into a string.
		String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(docref);
		
		System.out.println(output);
		
		//create client
		String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu2";
		//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
		//String serverBaseUrl = "http://phh-l-iopweb-l1:8080/hapi-fhir-jpaserver/baseDstu2";
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
				
		//use client to store a new resource instance
		MethodOutcome outcome = client.create().resource(docref).execute();
		System.out.println(outcome.getId());
		
	}

}