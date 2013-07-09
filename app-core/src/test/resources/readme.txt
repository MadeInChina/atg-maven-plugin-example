Two type of set config layer:

Start nucleus with modules.
mNucleus = NucleusTestUtils.startNucleusWithModules(new String[] {
         "DAS"
         },
         this.getClass(),//the prefix of config
         "config",//the suffix of config
         "/atg/dynamo/service/SMTPEmail");
the config path is "/src/test/resources/"+packagename+"/data/"+config

then you can get component like 
emailService = (EmailService) mNucleus.resolveName("EmailService");

alternative way:
 setConfigurationLocation("src/test/resources/config");
 
then get componentlike
emailService = (EmailService) resolveNucleusComponent("/atg/dynamo/service/EmailService");


