package uk.gov.nhsdigital.nrls.generate;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu.valueset.DocumentRelationshipTypeEnum;
import ca.uhn.fhir.model.dstu2.composite.AttachmentDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference.Content;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference.Context;
import ca.uhn.fhir.model.dstu2.resource.DocumentReference.RelatesTo;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader.Destination;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader.Response;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader.Source;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Parameters.Parameter;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.resource.Composition.Attester;
import ca.uhn.fhir.model.dstu2.resource.Composition.Section;
import ca.uhn.fhir.model.dstu2.resource.Practitioner.PractitionerRole;
import ca.uhn.fhir.model.dstu2.valueset.BundleTypeEnum;
import ca.uhn.fhir.model.dstu2.valueset.CompositionAttestationModeEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointSystemEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.DocumentReferenceStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.ResponseTypeEnum;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.StringDt;

public class CreateXML {

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static DocumentReference createDocumentReference(){
		
		final DocumentReference docref = new DocumentReference();
		
		docref.setId("13daadee-26e1-4d6a-9e6a-7f4af9b58877");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/nrls-documentreference-1-0");
		docref.setResourceMetadata(meta);
		
		IdentifierDt rr5 = new IdentifierDt();
		rr5.setSystem(" http://www.acme.com/identifiers/patient").setValue("b18f24b7-e72c-44c0-bdf4-7e45d06db1b6");
		docref.setMasterIdentifier(rr5);
		
		IdentifierDt rr4 = new IdentifierDt();
		rr4.setValue("18daadee-26e1-4d6a-9e6a-7f4af9b58877");
		List<IdentifierDt> idenList = new ArrayList<IdentifierDt>();
		idenList.add(rr4);
		docref.setIdentifier(idenList);
	
		  //Subject
		docref.setSubject(new ResourceReferenceDt().setReference("https://pds.proxy.nhs.uk/Patient/9409401122").setDisplay("Mrs Diane Hembury"));
		
		  // Coded types can naturally be set using plain strings
		CodingDt doctype = docref.getType().addCoding();
		doctype.setSystem("http://fhir.nhs.net/ValueSet/correspondence-document-type-1");
        doctype.setCode("861421000000109");
        doctype.setDisplay("End of Life Care Coordination Summary");
        
        //Class element not specified in nrls-documentreference-1-0 profile
//        CodingDt classtype = docref.getClassElement().addCoding();
//        classtype.setSystem("http://hl7.org/fhir/ValueSet/doc-classcodes");
//        //LOINC: LP173421-1 Report 
//        classtype.setCode("LP173421-1");
//        classtype.setDisplay("Report");
		
        //Author
        ResourceReferenceDt crr2 = new ResourceReferenceDt();
        crr2.setReference("Practitioner/70871").setDisplay("King's College Hospital");
        List<ResourceReferenceDt> crr2list = new ArrayList<ResourceReferenceDt>();
		crr2list.add(crr2);
		docref.setAuthor(crr2list);
        
		 //Custodian
		docref.setCustodian(new ResourceReferenceDt().setReference("Organization/70870").setDisplay("Coordinate My Care"));
      
		//Authenticator
		docref.setAuthenticator(new ResourceReferenceDt().setReference("Practitioner/70871").setDisplay("King's College Hospital"));
        
		docref.setCreated(new DateTimeDt("2016-03-08T15:26:00+01:00"));
		
		InstantDt dt = new InstantDt();
		dt.setValueAsString("2016-03-08T15:26:01+01:00");
		docref.setIndexed(dt);
		
		docref.setStatus(DocumentReferenceStatusEnum.CURRENT);
		
		//docStatus
        CodingDt docStat = docref.getDocStatus().addCoding();
        docStat.setSystem("http://hl7.org/fhir/ValueSet/composition-status");
        docStat.setCode("final");
        docStat.setDisplay("Final");
		
		 //Relationships to other documents not specified in nrls-documentreference-1-0 profile
//      RelatesTo rt = new RelatesTo();
//        //BROKEN
//// rt.setCode(DocumentRelationshipTypeEnum.REPLACES);// rt.setCode(new BoundCodeDt().setValueAsEnum(DocumentRelationshipTypeEnum.REPLACES));
////        rt.setCode(new BoundCodeDt().setValueAsEnum(DocumentRelationshipTypeEnum.REPLACES));
////        rt.getCodeElement(DocumentRelationshipTypeEnum.REPLACES);
// // rt.setCode(DocumentRelationshipTypeEnum.REPLACES);
//  
//      rt.setTarget(new ResourceReferenceDt().setReference("DocumentReference/122393").setDisplay("End of Life Care Coordination Summary"));
//      docref.addRelatesTo(rt);
//       // docref.setRelatesTo((List<RelatesTo>) rt);
       
		docref.setDescription("EOL");
        
        CodingDt seclabel = docref.getSecurityLabelFirstRep().addCoding();
        seclabel.setSystem("http://hl7.org/fhir/ValueSet/security-labels");
		seclabel.setCode("V");
		seclabel.setDisplay("very restricted");
		
        //Content
        List<Content> ContentList = new ArrayList<Content>();
		Content cont = new Content();
		cont.setAttachment(new AttachmentDt().setContentType("application/pdf").setUrl("https://gpsystem.nhs.uk/epcaccs/9409401122/EOLSummary.pdf").setSize(1000).setTitle("EPaCCs Place chosen to die summary"));
		//Format/content rules for the document not specified in nrls-documentreference-1-0 profile
		//cont.addFormat(new CodingDt().setSystem("http://hl7.org/fhir/ValueSet/formatcodes").setCode("urn:ihe:pcc:handp:2008").setDisplay("History and Physical Specification"));
		ContentList.add(cont);
		docref.setContent(ContentList);
		
		//Context
		Context contx = new Context();
		contx.getFacilityType().getCodingFirstRep().setSystem("http://hl7.org/fhir/ValueSet/c80-facilitycodes").setCode("83891005").setDisplay("Solo practice private office");
		docref.setContext(contx);
		
		return docref;
	}
		
}
