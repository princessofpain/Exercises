<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
	exclude-result-prefixes="fo"></xsl:stylesheet>
	<xsl:template match="LoanRepayment">
		<for:root xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" 
					margin="2cm">
					<fo:region-body/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">Loan type: 
						<xsl:value-of select="type"/>
					</fo:block>
					<fo:block font-size="10pt">
						<fo:table table-layout="fixed" width="100%" border-collapse="separate">
							<fo:table-column column-width="2cm">No</fo:table-column>
							<fo:table-column column-width="2cm">Amount</fo:table-column>
							<fo:table-column column-width="2cm">Rate</fo:table-column>
							<fo:table-column column-width="2cm">Interest</fo:table-column>
							<fo:table-column column-width="2cm">Total Pay</fo:table-column>
							<fo:table-column column-width="2cm">Rest</fo:table-column>
							
							<fo:table-body>
								<xsl:apply-templates select="month"/>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template match="month">
		<fo:table-row>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="id"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="rest"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="rate"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="interest"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="totalPay"/>
				</fo:block>
			</fo:table-cell>
			
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="newRest"/>
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
</xsl:stylesheet>