/**
 * An example script that handles adding or updating a groovy script via the REST API.
 */

import groovy.json.JsonOutput

@Grab('org.sonatype.nexus:nexus-rest-client:3.9.0-01')
@Grab('org.sonatype.nexus:nexus-rest-jackson2:3.9.0-01')
@Grab('org.sonatype.nexus:nexus-script:3.9.0-01')
@Grab('org.jboss.spec.javax.servlet:jboss-servlet-api_3.1_spec:1.0.0.Final')
@Grab('com.fasterxml.jackson.core:jackson-core:2.8.6')
@Grab('com.fasterxml.jackson.core:jackson-databind:2.8.6')
@Grab('com.fasterxml.jackson.core:jackson-annotations:2.8.6')
@Grab('com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:2.8.6')
@Grab('org.jboss.spec.javax.ws.rs:jboss-jaxrs-api_2.0_spec:1.0.1.Beta1')
@Grab('org.jboss.spec.javax.annotation:jboss-annotations-api_1.2_spec:1.0.0.Final')
@Grab('javax.activation:activation:1.1.1')
@Grab('net.jcip:jcip-annotations:1.0')
@Grab('org.jboss.logging:jboss-logging-annotations:2.0.1.Final')
@Grab('org.jboss.logging:jboss-logging-processor:2.0.1.Final')
@Grab('com.sun.xml.bind:jaxb-impl:2.2.7')
@Grab('com.sun.mail:javax.mail:1.5.6')
@Grab('org.apache.james:apache-mime4j:0.6')
@GrabExclude('org.codehaus.groovy:groovy-all')
import javax.ws.rs.NotFoundException

import org.sonatype.nexus.script.ScriptClient
import org.sonatype.nexus.script.ScriptXO

import org.jboss.resteasy.client.jaxrs.BasicAuthentication
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder

CliBuilder cli = new CliBuilder(
        usage: 'groovy wrapInJson.groovy -f scriptFile.groovy')
cli.with {
    f longOpt: 'file', args: 1, required: true, 'Script file to wrap'
}
def options = cli.parse(args)
if (!options) {
    return
}

def file = new File(options.f)
assert file.exists()

String name = options.n ?: file.name

def script = new ScriptXO(name, file.text, 'groovy')

println JsonOutput.toJson(script)